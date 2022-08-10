package ru.multicarta.shopping.service.serviceImpl;

import iso.std.ru.multicarta.tech.xsd.purchaserequest.PurchaseRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.multicarta.shopping.entity.Item;
import ru.multicarta.shopping.entity.Purchase;
import ru.multicarta.shopping.exception.ApiException;
import ru.multicarta.shopping.repository.PurchaseRepository;
import ru.multicarta.shopping.service.CustomerService;
import ru.multicarta.shopping.service.ItemService;
import ru.multicarta.shopping.service.PurchaseService;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final CustomerService customerService;
    private final ItemService itemService;
    private final JaxbServiceImpl jaxbService;

    @Transactional
    @Override
    public void performPurchase(String username, String purchaseRequestXml) {
        log.info("Performing purchase for customer with username {}. Purchase request: {}", username, purchaseRequestXml);

        PurchaseRequest purchaseRequest;
        try {
            purchaseRequest = jaxbService.parse(purchaseRequestXml, PurchaseRequest.class);
        } catch (JAXBException ex) {
            log.warn("An exception occurred while validating an xml message. Exception message: {}", ex.getMessage());
            throw new ApiException("106", "Couldn't parse the request body.");
        }

        var quantity = purchaseRequest.getQuantity();
        var itemId = purchaseRequest.getItemId();

        var customer = customerService.getByUsername(username)
                .orElseThrow(() -> new IllegalStateException("Customer doesn't exists"));

        var item = itemService.getItem(itemId.longValueExact())
                .orElseThrow(() -> new ApiException("100", "Item not found", HttpStatus.BAD_REQUEST));

        if (item.getQuantity() < quantity.intValueExact()) {
            throw new ApiException("101", "Not enough items for such purchase");
        }

        var amount = item.getAmount().multiply(new BigDecimal(quantity));
        item.setQuantity(item.getQuantity() - quantity.intValueExact());
        itemService.saveUpdatedItem(item);

        var purchase = Purchase.builder()
                .id(UUID.randomUUID())
                .count(quantity.intValueExact())
                .amount(amount)
                .item(Item.builder().id(itemId.longValueExact()).build())
                .customer(customer)
                .build();

        purchaseRepository.saveAndFlush(purchase);
    }

    @Override
    public List<Purchase> getPurchasesStartingFromDate(LocalDate startingDate) {
        return purchaseRepository.getPurchasesStartingFromDate(startingDate);
    }

    @Override
    public List<Purchase> getPurchasePage(Integer index, Integer offset) {
        log.info("Getting page with purchases. Index: {}, Offset: {}", index, offset);
        return purchaseRepository.findAll(PageRequest.of(index, offset, Sort.by("PURCHASE_DATE"))).getContent();
    }
}

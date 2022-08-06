package ru.multicarta.shopping.service.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.multicarta.shopping.dto.PurchaseRequest;
import ru.multicarta.shopping.entity.Item;
import ru.multicarta.shopping.entity.Purchase;
import ru.multicarta.shopping.exception.ApiException;
import ru.multicarta.shopping.repository.PurchaseRepository;
import ru.multicarta.shopping.service.CustomerService;
import ru.multicarta.shopping.service.ItemService;
import ru.multicarta.shopping.service.PurchaseService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final CustomerService customerService;
    private final ItemService itemService;

    @Transactional
    @Override
    public void performPurchase(String username, PurchaseRequest purchaseRequest) {
        log.info("Performing purchase for customer with username {}. Purchase details: {}", username, purchaseRequest);
        var requestedQuantity = purchaseRequest.getQuantity();
        var requestedItemId = purchaseRequest.getItemId();

        var existingCustomer = customerService.getByUsername(username)
                .orElseThrow(() -> new IllegalStateException("Customer doesn't exists"));

        var existingItem = itemService.getItem(requestedItemId)
                .orElseThrow(() -> new ApiException("100", "Item not found", HttpStatus.BAD_REQUEST));

        if (existingItem.getQuantity() < requestedQuantity) {
            throw new ApiException("101", "Not enough items for such purchase");
        }

        var amount = existingItem.getAmount().multiply(new BigDecimal(requestedQuantity));
        existingItem.setQuantity(existingItem.getQuantity() - requestedQuantity);
        itemService.saveUpdatedItem(existingItem);

        var purchase = Purchase.builder()
                .count(requestedQuantity)
                .amount(amount)
                .item(Item.builder().id(requestedItemId).build())
                .customer(existingCustomer)
                .build();

        purchaseRepository.saveAndFlush(purchase);
    }

    @Override
    public List<Purchase> getPurchasesStartingFromDate(LocalDate startingDate) {
        return purchaseRepository.getPurchasesStartingFromDate(startingDate);
    }
}

package ru.multicarta.shopping.service.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.multicarta.shopping.entity.Item;
import ru.multicarta.shopping.repository.ItemRepository;
import ru.multicarta.shopping.service.ItemService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public List<Item> getAllItems() {
        log.info("Getting all items...");
        return itemRepository.findAll();
    }

    @Override
    public List<Item> getAvailableItems() {
        return itemRepository.getItemsAvailableForSale();
    }

    @Override
    public List<Item> getItemPage(Integer index, Integer offset) {
        log.info("Getting page with items. Index: {}, Offset: {}", index, offset);
        return itemRepository.findAll(PageRequest.of(index, offset, Sort.by("id"))).getContent();
    }

    @Override
    public Optional<Item> getItem(Long itemId) {
        return itemRepository.findById(itemId);
    }

    @Override
    public void saveUpdatedItem(Item item) {
        itemRepository.save(item);
    }

    @SneakyThrows
    @Override
    public String getMonthlyBestSellerItem() {
        var bestSellerItemName = itemRepository.getMonthlyBestSellerItem();
        System.out.println("Monthly best seller item:\n" + bestSellerItemName);
        return bestSellerItemName;
    }
}

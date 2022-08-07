package ru.multicarta.shopping.service;

import ru.multicarta.shopping.entity.Item;
import ru.multicarta.shopping.entity.Purchase;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<Item> getItemPage(Integer index, Integer offset);

    Optional<Item> getItem(Long itemId);

    void saveUpdatedItem(Item item);

    Purchase getBestSellerItemStartingFromDate(LocalDate dateInPast);

    List<Item> getAllItems();
}

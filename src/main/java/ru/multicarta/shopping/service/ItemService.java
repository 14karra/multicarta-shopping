package ru.multicarta.shopping.service;

import ru.multicarta.shopping.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<Item> getItemPage(Integer index, Integer offset);

    Optional<Item> getItem(Long itemId);

    void saveUpdatedItem(Item item);

    String getMonthlyBestSellerItem();

    List<Item> getAllItems();

    List<Item> getAvailableItems();
}

package ru.multicarta.shopping.service;

import ru.multicarta.shopping.entity.Item;

import java.util.List;

public interface ItemService {

    List<Item> getItemPage(Integer index, Integer offset);
}

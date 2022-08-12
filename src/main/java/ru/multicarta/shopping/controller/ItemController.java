package ru.multicarta.shopping.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ru.multicarta.shopping.api.ItemApi;
import ru.multicarta.shopping.dto.Items;
import ru.multicarta.shopping.exception.ExceptionHandling;
import ru.multicarta.shopping.service.ItemService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ItemController extends ExceptionHandling implements ItemApi {

    private final ItemService itemService;

    @Override
    public Items getAllItems() {
        log.info("Request to get all items received.");
        return new Items(itemService.getAllItems());
    }

    @Override
    public Items getAvailableItems() {
        log.info("Request to get all available items received.");
        return new Items(itemService.getAvailableItems());
    }

    @Override
    public Items getItemPage(Integer index, Integer offset) {
        log.info("Request to get page with items received. Index: {}, Offset: {}", index, offset);
        return new Items(itemService.getItemPage(index, offset));
    }

    @Override
    public String getMonthlyBestSellerItem() {
        log.info("Request to get best-seller item from past month.");
        return itemService.getMonthlyBestSellerItem();
    }

    @Override
    public String getBestSellerItemFor18YearsOldCustomers() {
        log.info("Request to get best-seller item from 18 years old customers.");
        return itemService.getBestSellerItemFor18YearsOldCustomers();
    }
}

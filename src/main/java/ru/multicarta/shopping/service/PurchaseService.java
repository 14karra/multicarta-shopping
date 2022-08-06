package ru.multicarta.shopping.service;

import ru.multicarta.shopping.dto.PurchaseRequest;
import ru.multicarta.shopping.entity.Purchase;

import java.time.LocalDate;
import java.util.List;

public interface PurchaseService {

    void performPurchase(String username, PurchaseRequest purchaseRequest);

    List<Purchase> getPurchasesStartingFromDate(LocalDate startingDate);
}

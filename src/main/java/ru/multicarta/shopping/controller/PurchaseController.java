package ru.multicarta.shopping.controller;

import iso.std.ru.multicarta.tech.xsd.purchaserequest.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ru.multicarta.shopping.annotation.ValidateRequestBody;
import ru.multicarta.shopping.api.PurchaseApi;
import ru.multicarta.shopping.dto.Purchases;
import ru.multicarta.shopping.exception.ExceptionHandling;
import ru.multicarta.shopping.service.PurchaseService;

import java.time.LocalDate;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PurchaseController extends ExceptionHandling implements PurchaseApi {

    private final PurchaseService purchaseService;

    @ValidateRequestBody(PurchaseRequest.class)
    @Override
    public void postPerformPurchase(String purchaseRequestXml) {
        // TODO: 05.08.2022 After implementing the security, get the username from the SecurityContextHolder
        String username = "johnDoe";
        System.out.println("Entered the controller");
        purchaseService.performPurchase(username, purchaseRequestXml);
    }

    @Override
    public Purchases getPurchasesFromPassedDays(int daysPassed) {
        var dateInPast = LocalDate.now().minusDays(daysPassed);
        return new Purchases(purchaseService.getPurchasesStartingFromDate(dateInPast));
    }
}

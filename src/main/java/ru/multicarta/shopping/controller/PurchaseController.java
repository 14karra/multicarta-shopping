package ru.multicarta.shopping.controller;

import iso.std.ru.multicarta.tech.xsd.purchaserequest.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ru.multicarta.shopping.annotation.ValidateRequestBody;
import ru.multicarta.shopping.api.PurchaseApi;
import ru.multicarta.shopping.dto.Purchases;
import ru.multicarta.shopping.exception.ApiException;
import ru.multicarta.shopping.exception.ExceptionHandling;
import ru.multicarta.shopping.service.PurchaseService;
import ru.multicarta.shopping.service.UserService;

import java.time.LocalDate;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PurchaseController extends ExceptionHandling implements PurchaseApi {

    private final PurchaseService purchaseService;
    private final UserService userService;

    @ValidateRequestBody(PurchaseRequest.class)
    @Override
    public void postPerformPurchase(String purchaseRequestXml) {
        var expectedUsername = userService.getCurrentlyAuthenticatedUsername();
        expectedUsername.ifPresentOrElse(username -> {
            System.out.println("passed username retrieval from security context.");
            purchaseService.performPurchase(username, purchaseRequestXml);
        }, () -> {
            throw new ApiException("111", "User not authorized.");
        });
    }

    @Override
    public Purchases getPurchasesFromPassedDays(int daysPassed) {
        var dateInPast = LocalDate.now().minusDays(daysPassed);
        return new Purchases(purchaseService.getPurchasesStartingFromDate(dateInPast));
    }
}

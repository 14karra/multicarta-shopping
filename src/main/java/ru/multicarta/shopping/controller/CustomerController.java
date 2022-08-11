package ru.multicarta.shopping.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ru.multicarta.shopping.api.CustomerApi;
import ru.multicarta.shopping.dto.Customers;
import ru.multicarta.shopping.exception.ExceptionHandling;
import ru.multicarta.shopping.service.CustomerService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CustomerController extends ExceptionHandling implements CustomerApi {

    private final CustomerService customerService;

    @Override
    public Customers getAllCustomers() {
        log.info("Request to get all customers received.");
        return new Customers(customerService.getAllCustomers());
    }

    @Override
    public Customers getCustomerPage(Integer index, Integer offset) {
        log.info("Request to get page with customers received. Index: {}, Offset: {}", index, offset);
        return new Customers(customerService.getCustomerPage(index, offset));
    }
}

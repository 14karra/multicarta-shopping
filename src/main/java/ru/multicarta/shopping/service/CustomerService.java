package ru.multicarta.shopping.service;

import ru.multicarta.shopping.entity.Customer;

import java.util.Optional;

public interface CustomerService {

    Optional<Customer> getByUsername(String username);
}

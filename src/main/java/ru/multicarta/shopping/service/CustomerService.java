package ru.multicarta.shopping.service;

import ru.multicarta.shopping.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Optional<Customer> getByUsername(String username);

    void saveCustomer(Customer customer);

    List<Customer> getAllCustomers();

    List<Customer> getCustomerPage(Integer index, Integer offset);
}

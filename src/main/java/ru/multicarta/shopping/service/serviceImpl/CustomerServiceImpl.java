package ru.multicarta.shopping.service.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.multicarta.shopping.entity.Customer;
import ru.multicarta.shopping.repository.CustomerRepository;
import ru.multicarta.shopping.service.CustomerService;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Optional<Customer> getByUsername(String username) {
        return customerRepository.findCustomerByUsername(username);
    }
}

package ru.multicarta.shopping.service.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.multicarta.shopping.entity.Customer;
import ru.multicarta.shopping.repository.CustomerRepository;
import ru.multicarta.shopping.service.CustomerService;

import java.util.List;
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

    @Override
    public void saveCustomer(Customer customer) {
        log.info("Saving a new customer.");
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        log.info("Getting all customers...");
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> getCustomerPage(Integer index, Integer offset) {
        log.info("Getting page with customers. Index: {}, Offset: {}", index, offset);
        return customerRepository.findAll(PageRequest.of(index, offset)).getContent();
    }

    @Override
    public String get6MonthsBestCustomer() {
        System.out.println("Getting 6 months best customer...");
        return customerRepository.get6MonthsBestCustomer();
    }
}

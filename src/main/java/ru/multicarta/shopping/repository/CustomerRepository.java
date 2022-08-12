package ru.multicarta.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.multicarta.shopping.entity.Customer;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findCustomerByUsername(String username);

    @Query(value = "SELECT customer.name||' '||customer.last_name " +
            "   FROM customer " +
            "INNER JOIN purchase " +
            "ON purchase.customer_id = customer.id " +
            "WHERE purchase.purchase_date BETWEEN (CURRENT_DATE - INTERVAL '6 MONTH') AND CURRENT_DATE " +
            "GROUP BY customer.id " +
            "ORDER BY count(purchase.count) DESC LIMIT 1",
            nativeQuery = true)
    String get6MonthsBestCustomer();
}

package ru.multicarta.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.multicarta.shopping.entity.Purchase;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {

    @Query(value = "SELECT p from Purchase p WHERE p.purchaseDate > ?1")
    List<Purchase> getPurchasesStartingFromDate(LocalDate startingDate);
}

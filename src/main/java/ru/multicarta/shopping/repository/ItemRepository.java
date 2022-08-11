package ru.multicarta.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.multicarta.shopping.entity.Item;
import ru.multicarta.shopping.entity.Purchase;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    // TODO: 06.08.2022 Rewrite this query correctly before testing
    @Query(value = "SELECT i FROM ITEM AS i " +
            "INNER JOIN PURCHASE p " +
            "ON i.ID = p.ITEM_ID " +
            "GROUP BY p.ITEM_ID, p.PURCHASE_DATE, i " +
            "HAVING p.PURCHASE_DATE > ?1 " +
            "ORDER BY COUNT(p.count) DESC " +
            "LIMIT 1;",
            nativeQuery = true)
    Purchase getBestSellerItemStartingFromDate(LocalDate dateInPast);

    @Query(value = "SELECT * FROM ITEM AS i " +
            "WHERE i.quantity > 0;",
            nativeQuery = true)
    List<Item> getItemsAvailableForSale();
}

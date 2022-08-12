package ru.multicarta.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.multicarta.shopping.entity.Item;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "SELECT * FROM ITEM AS i " +
            "WHERE i.quantity > 0;",
            nativeQuery = true)
    List<Item> getItemsAvailableForSale();

    @Query(value = "SELECT i.name " +
            "FROM item AS i " +
            "INNER JOIN purchase " +
            "ON purchase.item_id = i.id " +
            "WHERE purchase.purchase_date BETWEEN (CURRENT_DATE - INTERVAL '1 MONTH') AND CURRENT_DATE " +
            "GROUP BY name " +
            "ORDER BY sum(purchase.count) DESC LIMIT 1",
            nativeQuery = true)
    String getMonthlyBestSellerItem();
}

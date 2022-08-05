package ru.multicarta.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.multicarta.shopping.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}

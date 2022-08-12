package ru.multicarta.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.multicarta.shopping.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}

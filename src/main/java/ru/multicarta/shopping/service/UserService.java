package ru.multicarta.shopping.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.multicarta.shopping.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    void performRegistration(String userRegistrationRequestXml);

    List<User> getAllUsers();

    Optional<String> getCurrentlyAuthenticatedUsername();
}

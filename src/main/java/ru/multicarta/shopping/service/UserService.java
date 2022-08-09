package ru.multicarta.shopping.service;

import ru.multicarta.shopping.entity.User;

import java.util.List;

public interface UserService {

    void performRegistration(String userRegistrationRequestXml);

    List<User> getAllUsers();
}

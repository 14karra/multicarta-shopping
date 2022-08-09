package ru.multicarta.shopping.controller;

import iso.std.ru.multicarta.tech.xsd.userregistrationrequest.UserRegistrationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ru.multicarta.shopping.annotation.ValidateRequestBody;
import ru.multicarta.shopping.api.UserApi;
import ru.multicarta.shopping.dto.Users;
import ru.multicarta.shopping.exception.ExceptionHandling;
import ru.multicarta.shopping.service.UserService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController extends ExceptionHandling implements UserApi {

    private final UserService userService;

    @ValidateRequestBody(UserRegistrationRequest.class)
    @Override
    public void postUserRegistrationRequest(String userRegistrationRequestXml) {
        log.info("Request to register a user received.");
        userService.performRegistration(userRegistrationRequestXml);
    }

    @Override
    public Users getAllUsers() {
        log.info("Request to fetch all users received.");
        return new Users(userService.getAllUsers());
    }
}

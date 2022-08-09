package ru.multicarta.shopping.service.serviceImpl;

import iso.std.ru.multicarta.tech.xsd.userregistrationrequest.UserRegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.multicarta.shopping.entity.Customer;
import ru.multicarta.shopping.entity.User;
import ru.multicarta.shopping.exception.ApiException;
import ru.multicarta.shopping.repository.UserRepository;
import ru.multicarta.shopping.service.CustomerService;
import ru.multicarta.shopping.service.UserService;

import javax.xml.bind.JAXBException;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CustomerService customerService;
    private final JaxbServiceImpl jaxbService;
    // TODO: 09.08.2022 Uncomment this line after implementing security
    // private final EncoderService encoderService;

    @Transactional
    @Override
    public void performRegistration(String userRegistrationRequestXml) {
        log.info("Performing registration for a new user.");

        UserRegistrationRequest userRegistrationRequest;
        try {
            userRegistrationRequest = jaxbService.parse(userRegistrationRequestXml, UserRegistrationRequest.class);
        } catch (JAXBException ex) {
            log.warn("An exception occurred while validating an xml message. Exception message: {}", ex.getMessage());
            throw new ApiException("106", "Couldn't parse the request body.");
        }

        var username = userRegistrationRequest.getUsername();

        var existingUser = userRepository.findById(username);
        if (existingUser.isPresent()) {
            throw new ApiException("109", "Такой логин уже занят!");
        }

        if (customerService.getByUsername(username).isPresent()) {
            throw new ApiException("110", "Ошибка состояния сервера. Связываетесь с администратором сервера");
        }

        var customer = Customer.builder()
                .id(UUID.randomUUID())
                .name(userRegistrationRequest.getUsername())
                .lastName(userRegistrationRequest.getLastName())
                .birthday(new Date(userRegistrationRequest.getBirthday().toGregorianCalendar().getTime().getTime()))
                .username(username)
                .build();

        customerService.saveCustomer(customer);

        var user = User.builder()
                .username(username)
                // TODO: 09.08.2022 Uncomment this line after implementing security. Delete the line after that one.
                // .password(encoderService.encode(userRegistrationRequest.getPassword()))
                .password(userRegistrationRequest.getPassword())
                .build();

        userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

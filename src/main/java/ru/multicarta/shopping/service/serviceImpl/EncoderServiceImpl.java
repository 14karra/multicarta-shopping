package ru.multicarta.shopping.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.multicarta.shopping.service.EncoderService;


@Service
public class EncoderServiceImpl implements EncoderService {

    @Autowired
    PasswordEncoder passwordEncoder;

    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
}

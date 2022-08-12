package ru.multicarta.shopping.controller;

import org.springframework.stereotype.Controller;
import ru.multicarta.shopping.api.FrontendApi;

@Controller
public class FrontendController implements FrontendApi {

    @Override
    public String redirect() {
        return "forward:/";
    }
}
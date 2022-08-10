package ru.multicarta.shopping.service;

@FunctionalInterface
public interface EncoderService {

    String encode(String password);
}

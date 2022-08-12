package ru.multicarta.shopping.service;

import javax.xml.bind.JAXBException;

public interface JaxbService {

    <T> T parse(String xmlMessage, Class<T> clazz) throws JAXBException;

    <T> String stringify(T object, Class<?> clazz) throws JAXBException;
}

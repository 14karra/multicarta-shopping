package ru.multicarta.shopping.exception;

import javax.validation.Payload;

public class InvalidXmlException extends RuntimeException implements Payload {

    public InvalidXmlException() {
        super("Invalid xml");
    }
}

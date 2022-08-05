package ru.multicarta.shopping.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
    private final String code;
    private final HttpStatus httpStatus;

    public ApiException(String code, String msg, HttpStatus httpStatus) {
        super(msg);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public ApiException(String code, String msg) {
        super(msg);
        this.code = code;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }
}
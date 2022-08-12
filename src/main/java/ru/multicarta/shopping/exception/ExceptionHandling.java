package ru.multicarta.shopping.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class ExceptionHandling {

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<Object> handleApiViolation(ApiException exception) {
        return new ResponseEntity<>(ApiError.builder()
                .message(exception.getMessage())
                .code(exception.getCode())
                .build(), exception.getHttpStatus());
    }
}

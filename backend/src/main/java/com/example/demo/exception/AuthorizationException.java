package com.example.demo.exception;
import com.example.demo.enums.ResponseEnum;

public class AuthorizationException extends RuntimeException {
    public AuthorizationException() {
        super(ResponseEnum.NOT_AUTHORIZATION.getMessage());
    }

    public AuthorizationException(String message) {
        super(message);
    }
}

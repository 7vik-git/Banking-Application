package com.gevernova.banking_application.exceptions;

public class UserNameNotAvailableException extends RuntimeException {
    public UserNameNotAvailableException(String message) {
        super(message);
    }
}

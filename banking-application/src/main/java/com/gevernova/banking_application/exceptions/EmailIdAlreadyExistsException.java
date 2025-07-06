package com.gevernova.banking_application.exceptions;

public class EmailIdAlreadyExistsException extends RuntimeException {
    public EmailIdAlreadyExistsException(String message) {
        super(message);
    }
}

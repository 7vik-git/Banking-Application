package com.gevernova.banking_application.exceptions;

public class OtpNotFoundException extends RuntimeException{
    public OtpNotFoundException(String message){
        super(message);
    }
}

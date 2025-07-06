package com.gevernova.banking_application.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterUserApiResponse<T> {

    //created this DTO to just provide custom response to users when they register for the first time
    // now with this custom repose dto they get the message saying registration successful,
    // now verify with otp sent to their mail
    private String message;
    private T data;
}

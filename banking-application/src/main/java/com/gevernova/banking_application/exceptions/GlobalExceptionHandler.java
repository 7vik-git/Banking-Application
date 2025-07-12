package com.gevernova.banking_application.exceptions;

import com.gevernova.banking_application.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleAccountNotFound(AccountNotFoundException exception){
        log.warn(exception.getMessage());
        return new ResponseEntity<>(new ResponseDTO(exception.getMessage()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ResponseDTO> handleInsufficientBalance(InsufficientBalanceException exception){
        log.warn(exception.getMessage());
        return new ResponseEntity<>(new ResponseDTO(exception.getMessage()),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNameNotAvailableException.class)
    public ResponseEntity<ResponseDTO> handleUsernameNotAvailable(UserNameNotAvailableException exception){
        log.warn(exception.getMessage());
        return new ResponseEntity<>(new ResponseDTO(exception.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailIdAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailIDAlreadyExists(EmailIdAlreadyExistsException exception){
        log.warn(exception.getMessage());
        return ResponseEntity.badRequest().body("An account with this email already exists");
    }

    @ExceptionHandler(OtpNotFoundException.class)
    public ResponseEntity<String> handleOtpNotFound(OtpNotFoundException exception){
        return ResponseEntity.badRequest().body("OTP not requested or Invalid OTP");
    }

    @ExceptionHandler(OtpExpiredException.class)
    public  ResponseEntity<String> handleOtpExpired(OtpExpiredException exception){
        return ResponseEntity.badRequest().body("OTP expired, request a new OTP");
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<String> handleWrongPassword(WrongPasswordException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(UserNotVerifiedException.class)
    public ResponseEntity<String> handleUserNotVerified(UserNotVerifiedException exception){
        return ResponseEntity.internalServerError().body(exception.getMessage());
    }
}

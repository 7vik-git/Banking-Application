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
        return new ResponseEntity<>(new ResponseDTO(exception.getMessage()),HttpStatus.BAD_REQUEST);
    }
}

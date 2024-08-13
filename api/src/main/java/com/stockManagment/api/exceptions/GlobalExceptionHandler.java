package com.stockManagment.api.exceptions;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(LockedException.class)
//    public ResponseEntity<ExceptionDto> handleException(LockedException exception){
//
//    }
//    @ExceptionHandler(DisabledException.class)
//    public ResponseEntity<ExceptionDto> handleException(DisabledException exception){
//
//    }
//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<ExceptionDto> handleException(BadCredentialsException exception){
//
//    }
//    @ExceptionHandler(MessagingException.class)
//    public ResponseEntity<ExceptionDto> handleException(MessagingException exception){
//
//    }
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ExceptionDto> handleException(MessagingException exception){
//
//    }
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ExceptionDto> handleException(Exception exception){
//
//    }

}

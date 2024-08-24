package com.stockManagment.api.exceptions;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashSet;
import java.util.Set;

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
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleException(EntityNotFoundException exception){
        exception.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ExceptionDto.builder()
                        .errorCode(exception.getErrorCodes().getCode())
                        .businessDescribtion(exception.getErrorCodes().getDescription())
                        .errorDescription(exception.getErrorCodes().getDescription())
                        .build());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDto> handleException(MethodArgumentNotValidException exception){
        Set<String> errors = new HashSet<>();
        exception.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var errorMessage = error.getDefaultMessage();
                    errors.add(errorMessage);
                });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionDto.builder()
                        .validationErrors(errors)
                        .build());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleException(Exception exception){
        exception.printStackTrace();   //pour faire un log pour l'erreur
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionDto.builder()
                                .businessDescribtion("internal error,please contact the admin")
                                .errorDescription(exception.getMessage())
                                .build()
                );
    }

}

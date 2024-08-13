package com.stockManagment.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCodes {
    //codes here

    ;
    private int code ;
    private HttpStatus httpStatus ;
    private String description ;
}

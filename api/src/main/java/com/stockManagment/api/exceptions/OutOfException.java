package com.stockManagment.api.exceptions;

import lombok.Getter;

@Getter
public class OutOfException extends RuntimeException{
    private ErrorCodes errorCodes;
    private Double sommeRestante;
    private Integer qttRestante;

    public OutOfException(ErrorCodes errorCodes) {
        this.errorCodes = errorCodes;
    }

    public OutOfException(String message,ErrorCodes errorCodes) {
        super(message);
        this.errorCodes = errorCodes;
    }

    public OutOfException(String message,ErrorCodes errorCodes,Integer qttRestante) {
        super(message);
        this.errorCodes = errorCodes;
        this.qttRestante = qttRestante;
    }

    public OutOfException(String message,ErrorCodes errorCodes,Double sommeRestante) {
        super(message);
        this.errorCodes = errorCodes;
        this.sommeRestante = sommeRestante ;
    }


    public OutOfException(Throwable cause,ErrorCodes errorCodes,Double sommeRestante) {
        super(cause);
        this.errorCodes = errorCodes;
        this.sommeRestante = sommeRestante;
    }

    public OutOfException(Throwable cause,ErrorCodes errorCodes,Integer qttRestante) {
        super(cause);
        this.errorCodes = errorCodes;
        this.qttRestante = qttRestante;
    }
}

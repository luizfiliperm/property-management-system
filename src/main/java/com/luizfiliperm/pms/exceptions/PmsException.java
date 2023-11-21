package com.luizfiliperm.pms.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PmsException extends RuntimeException {
    private final HttpStatus httpStatus;

    public PmsException(String msg, HttpStatus httpStatus){
        super(msg);
        this.httpStatus = httpStatus;
    }

}

package com.luizfiliperm.pms.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    ResponseEntity<ErrorMessage> handleException(PmsException ex){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        return new ResponseEntity<>(new ErrorMessage(ex.getMessage(),ex.getHttpStatus().value(), System.currentTimeMillis(), request.getRequestURI()), ex.getHttpStatus());
    }
}

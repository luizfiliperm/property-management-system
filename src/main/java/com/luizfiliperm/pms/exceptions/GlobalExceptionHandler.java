package com.luizfiliperm.pms.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    ResponseEntity<ErrorMessage> handleException(PmsException ex){
        return getResponseEntity(ex.getHttpStatus(), ex.getMessage());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, PropertyReferenceException.class})
    ResponseEntity<ErrorMessage> handleBadRequestExceptions(Exception ex){
        return getResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());

    }

    private static ResponseEntity<ErrorMessage> getResponseEntity(HttpStatus httpStatus, String message){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        return new ResponseEntity<>(new ErrorMessage(message, httpStatus.value(), System.currentTimeMillis(), request.getRequestURI()), httpStatus);
    }
}

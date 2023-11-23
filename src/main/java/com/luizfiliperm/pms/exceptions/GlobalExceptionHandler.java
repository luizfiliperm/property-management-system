package com.luizfiliperm.pms.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    ResponseEntity<ErrorMessage> handleException(PmsException ex){
        return getResponseEntity(ex.getHttpStatus(), ex.getMessage());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, PropertyReferenceException.class, HttpMessageNotReadableException.class})
    ResponseEntity<ErrorMessage> handleBadRequestExceptions(Exception ex){
        return getResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());

    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    ResponseEntity<ValidationErrorMessage> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){

        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        return getValidationErrorMessage(errors);
    }

    private static ResponseEntity<ErrorMessage> getResponseEntity(HttpStatus httpStatus, String message){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        return new ResponseEntity<>(new ErrorMessage(message, httpStatus.value(), System.currentTimeMillis(), request.getRequestURI()), httpStatus);
    }

    private static ResponseEntity<ValidationErrorMessage> getValidationErrorMessage(List<String> errors){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        return new ResponseEntity<>(new ValidationErrorMessage("Validation Error", HttpStatus.BAD_REQUEST.value(), errors, System.currentTimeMillis(), request.getRequestURI()), HttpStatus.BAD_REQUEST);
    }
}

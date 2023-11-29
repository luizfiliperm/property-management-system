package com.luizfiliperm.pms.util;

import com.luizfiliperm.pms.exceptions.PmsException;
import org.springframework.http.HttpStatus;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    private final static String pattern = "dd/MM/yyyy";

    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

    public static String convertLocalDateToString(LocalDate date){
        if(date == null){
            return null;
        }
        return date.format(formatter);
    }

    public static LocalDate convertStringToLocalDate(String date){
        try {
            if(date == null){
                return null;
            }
            return java.time.LocalDate.parse(date, formatter);

        }catch (DateTimeException e){
            throw new PmsException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

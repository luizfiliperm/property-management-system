package com.luizfiliperm.pms.error;

import com.luizfiliperm.pms.exceptions.ValidationErrorMessage;

import java.util.List;

public class ErrorCreator {
    public static List<String> getValidationErrorsList(){
        return List.of("The description is required",
                "The name is required",
                "The street is required");
    }


}

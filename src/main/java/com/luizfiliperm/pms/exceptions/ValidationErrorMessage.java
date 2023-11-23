package com.luizfiliperm.pms.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrorMessage {
    private String message;
    private int status;
    private List<String> errors;
    private Long timestamp;
    private String path;
}

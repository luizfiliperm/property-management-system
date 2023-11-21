package com.luizfiliperm.pms.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    private String message;
    private int status;
    private Long timestamp;
    private String path;
}

package com.william.hubfintech.exceptions;

import lombok.Data;

@Data
public class BeanValidationException extends RuntimeException {

    private String message;

    public BeanValidationException(String message) {
        this.message = message;
    }
}

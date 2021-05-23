package com.william.hubfintech.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceAlreadyExistsException extends RuntimeException {
    private String message;
}

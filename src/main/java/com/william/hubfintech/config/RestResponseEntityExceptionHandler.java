package com.william.hubfintech.config;

import com.william.hubfintech.exceptions.BeanValidationException;
import com.william.hubfintech.exceptions.InvalidValueException;
import com.william.hubfintech.exceptions.ResourceAlreadyExistsException;
import com.william.hubfintech.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class JsonResponse {
        String message;
    }

    @ExceptionHandler(value = BeanValidationException.class)
    public ResponseEntity<JsonResponse> handleException(BeanValidationException e) {
        String bodyOfResponse = e.getMessage();
        return new ResponseEntity<JsonResponse>(new JsonResponse(bodyOfResponse), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<JsonResponse> handleException(ResourceNotFoundException e) {
        String bodyOfResponse = "O recurso não pode ser encontrado!";
        return new ResponseEntity<JsonResponse>(new JsonResponse(bodyOfResponse), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ResourceAlreadyExistsException.class)
    public ResponseEntity<JsonResponse> handleException(ResourceAlreadyExistsException e) {
        String bodyOfResponse = "O recurso já existe no sistema!";
        return new ResponseEntity<JsonResponse>(new JsonResponse(bodyOfResponse), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidValueException.class)
    public ResponseEntity<JsonResponse> handleException(InvalidValueException e) {
        String bodyOfResponse = e.getMessage();
        return new ResponseEntity<JsonResponse>(new JsonResponse(bodyOfResponse), HttpStatus.BAD_REQUEST);
    }
}

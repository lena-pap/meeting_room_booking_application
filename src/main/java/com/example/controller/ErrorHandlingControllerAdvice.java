package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This class helps with handling validation errors.
 * When a validation error happens, the response sent to the client
 * should be more comprehensible.
*/
@ControllerAdvice
public class ErrorHandlingControllerAdvice {
    /**
     * When a `MethodArgumentNotValidException` happens, this method will return
     * the error messages of the validations.
     * 
     * @param ex
     * @return ResponseEntity<Map<String, List<String>>> key: 'errors', value: An array with strings,
     * each representing a different validation error.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = MethodArgumentNotValidException.errorsToStringList(ex.getAllErrors());

        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}

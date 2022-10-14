package com.fatihk.examples.springcloud.foa.customer.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class AppExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Map<String,String>> methodArgumentNotValidException(final MethodArgumentNotValidException ex) {
        log.error("Validation error", ex);

        List list = ex.getBindingResult().getAllErrors().stream()
               .map(fieldError -> fieldError.getCode() + ":" + fieldError.getDefaultMessage())
               .collect(Collectors.toList());

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
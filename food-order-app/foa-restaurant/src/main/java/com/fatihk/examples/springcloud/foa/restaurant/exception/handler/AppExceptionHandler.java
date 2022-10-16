package com.fatihk.examples.springcloud.foa.restaurant.exception.handler;

import com.fatihk.examples.springcloud.foa.restaurant.exception.CustomErrorResponse;
import com.fatihk.examples.springcloud.foa.restaurant.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class AppExceptionHandler {

    @ExceptionHandler({MissingServletRequestParameterException.class, ResourceNotFoundException.class})
    public ResponseEntity<CustomErrorResponse> genericException(final Exception ex) {

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(ex.getMessage());
        errors.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<CustomErrorResponse>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
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
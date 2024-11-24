package com.shareskills.api.exception;

import com.shareskills.api.response.ResponseJson;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseJson<String> handleBadRequestException(BadRequestException ex) {
        return new ResponseJson<>(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    }
}

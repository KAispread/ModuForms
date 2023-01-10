package com.modu.ModuForm.app.web.controller;

import com.modu.ModuForm.app.exception.invalid.InvalidValueException;
import com.modu.ModuForm.app.exception.nosuch.NoSuchValueException;
import com.modu.ModuForm.app.exception.notfound.NotFoundException;
import com.modu.ModuForm.app.web.dto.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String LOG_FORMAT = "Error Class : {}, Code : {}, Message : {}";

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ExceptionResponse handleNotFoundException(final NotFoundException e) {
        log.info(LOG_FORMAT, e.getClass(), e.getErrorCode().getCode(), e.getMessage());
        return new ExceptionResponse(e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchValueException.class)
    public ExceptionResponse handleNoSuchValueException(final NoSuchValueException e) {
        log.info(LOG_FORMAT, e.getClass(), e.getErrorCode().getCode(), e.getMessage());
        return new ExceptionResponse(e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidValueException.class)
    public ExceptionResponse handleNoSuchValueException(final InvalidValueException e) {
        log.info(LOG_FORMAT, e.getClass(), e.getErrorCode().getCode(), e.getMessage());
        return new ExceptionResponse(e);
    }
}

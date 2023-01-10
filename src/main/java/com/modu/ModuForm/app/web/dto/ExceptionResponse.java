package com.modu.ModuForm.app.web.dto;

import com.modu.ModuForm.app.exception.CustomException;
import com.modu.ModuForm.app.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ExceptionResponse {
    private final String message;
    private final ErrorCode errorCode;

    public ExceptionResponse(final String message, final ErrorCode errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public ExceptionResponse(final CustomException exception) {
        this.message = exception.getMessage();
        this.errorCode = exception.getErrorCode();
    }
}

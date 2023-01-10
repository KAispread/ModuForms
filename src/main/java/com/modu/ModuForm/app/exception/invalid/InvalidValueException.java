package com.modu.ModuForm.app.exception.invalid;

import com.modu.ModuForm.app.exception.CustomException;
import com.modu.ModuForm.app.exception.ErrorCode;

public class InvalidValueException extends CustomException {
    public InvalidValueException(ErrorCode errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}

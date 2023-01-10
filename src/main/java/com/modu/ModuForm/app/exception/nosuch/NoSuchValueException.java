package com.modu.ModuForm.app.exception.nosuch;

import com.modu.ModuForm.app.exception.CustomException;
import com.modu.ModuForm.app.exception.ErrorCode;

public class NoSuchValueException extends CustomException {
    public NoSuchValueException(ErrorCode errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}

package com.modu.ModuForm.app.exception.notfound;

import com.modu.ModuForm.app.exception.CustomException;
import com.modu.ModuForm.app.exception.ErrorCode;

public class NotFoundException extends CustomException {
    public NotFoundException(ErrorCode errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}

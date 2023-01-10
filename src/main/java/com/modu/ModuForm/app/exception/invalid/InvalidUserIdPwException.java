package com.modu.ModuForm.app.exception.invalid;

import static com.modu.ModuForm.app.exception.ErrorCode.INVALID_USER_ID_PW;

public class InvalidUserIdPwException extends InvalidValueException {

    public InvalidUserIdPwException() {
        super(INVALID_USER_ID_PW, "잘못된 아이디 또는 패스워드입니다");
    }
}

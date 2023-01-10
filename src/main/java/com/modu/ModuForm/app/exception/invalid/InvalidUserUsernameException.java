package com.modu.ModuForm.app.exception.invalid;

import static com.modu.ModuForm.app.exception.ErrorCode.INVALID_USER_USERNAME;

public class InvalidUserUsernameException extends InvalidValueException {

    public InvalidUserUsernameException() {
        super(INVALID_USER_USERNAME, "해당 닉네임을 가진 유저가 존재하지 않습니다.");
    }
}

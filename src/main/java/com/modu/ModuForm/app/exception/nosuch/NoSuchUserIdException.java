package com.modu.ModuForm.app.exception.nosuch;

import static com.modu.ModuForm.app.exception.ErrorCode.NO_SUCH_USER_ID;

public class NoSuchUserIdException extends NoSuchValueException {

    public NoSuchUserIdException() {
        super(NO_SUCH_USER_ID, "해당 유저가 존재하지 않습니다.");
    }
}

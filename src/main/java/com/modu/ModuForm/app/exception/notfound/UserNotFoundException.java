package com.modu.ModuForm.app.exception.notfound;

import static com.modu.ModuForm.app.exception.ErrorCode.USER_NOT_FOUND;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException() {
        super(USER_NOT_FOUND, "유저를 찾을 수 없습니다.");
    }
}

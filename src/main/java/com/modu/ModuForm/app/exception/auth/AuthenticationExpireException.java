package com.modu.ModuForm.app.exception.auth;

import com.modu.ModuForm.app.exception.CustomException;

import static com.modu.ModuForm.app.exception.ErrorCode.AUTH_EXPIRED;

public class AuthenticationExpireException extends CustomException {
    public AuthenticationExpireException() {
        super(AUTH_EXPIRED, "만료된 토큰입니다");
    }
}

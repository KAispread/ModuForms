package com.modu.ModuForm.app.web.security.authentication.jwt;

import lombok.Getter;

@Getter
public enum JwtType {
    ACCESS("AccessToken"),
    REFRESH("RefreshToken");

    private final String tokenFlag;

    JwtType(String tokenFlag) {
        this.tokenFlag = tokenFlag;
    }
}

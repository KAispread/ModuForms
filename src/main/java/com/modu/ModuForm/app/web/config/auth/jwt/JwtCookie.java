package com.modu.ModuForm.app.web.config.auth.jwt;

public enum JwtCookie {
    NORMAL("normalJwt"),
    ENCRYPT("encryptJwt");

    private final String cookieName;

    JwtCookie(String cookieName) {
        this.cookieName = cookieName;
    }

    public String getCookieName() {
        return cookieName;
    }
}

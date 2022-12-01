package com.modu.ModuForm.app.web.config.auth.jwt;

import javax.servlet.http.Cookie;

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
    public boolean isCookiesContainToken(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            if (cookieName.equals(this.cookieName)) {
                return true;
            }
        }
        return false;
    }
}

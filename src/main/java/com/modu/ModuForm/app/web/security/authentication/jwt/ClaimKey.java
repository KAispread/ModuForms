package com.modu.ModuForm.app.web.security.authentication.jwt;

import io.jsonwebtoken.Claims;
import lombok.Getter;

@Getter
public enum ClaimKey {
    ID("id"),
    NAME("name"),
    NICKNAME("nickname"),
    EMAIL("email"),
    ROLE("role");

    private final String key;

    ClaimKey(String key) {
        this.key = key;
    }

    public Long getLongValue(Claims claims) {
        return (Long) claims.get(this.key);
    }

    public String getStringValue(Claims claims) {
        return String.valueOf(claims.get(this.key));
    }
}

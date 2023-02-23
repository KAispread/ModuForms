package com.modu.ModuForm.app.domain.user.common;

import lombok.Getter;

@Getter
public enum Role {
    USER("ROLE_USER", "일반 사용자"),
    GUEST("ROLE_GUEST", "손님");

    private final String key;
    private final String title;

    Role(String key, String title) {
        this.key = key;
        this.title = title;
    }
}

package com.modu.ModuForm.app.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum Gender {
    MAN("남자"),
    WOMAN("여자");

    private final String title;

    Gender(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

package com.modu.ModuForm.app.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

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

    public static String convertTitle(Gender gender) {
        if (gender == null) {
            return "null";
        }
        return gender.title;
    }

    public static Gender convertTitleToGender(String title) {
        return Arrays.stream(Gender.values()).filter(gender -> gender.title.equals(title)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 성별입니다"));
    }
}

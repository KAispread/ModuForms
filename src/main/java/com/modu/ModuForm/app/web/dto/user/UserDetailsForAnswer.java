package com.modu.ModuForm.app.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserDetailsForAnswer {
    private String userNickname;
    private String email;

    @Builder
    public UserDetailsForAnswer(String userNickname, String email) {
        this.userNickname = userNickname;
        this.email = email;
    }
}

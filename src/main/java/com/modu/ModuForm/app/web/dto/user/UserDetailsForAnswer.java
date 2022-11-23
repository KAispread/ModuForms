package com.modu.ModuForm.app.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserDetailsForAnswer {
    private Long userId;
    private String userNickname;
    private String email;
    private Boolean anonymousFlag;

    @Builder
    public UserDetailsForAnswer(Long userId, String userNickname, String email, Boolean anonymousFlag) {
        this.userNickname = userNickname;
        this.email = email;
        this.anonymousFlag = anonymousFlag;
    }
}

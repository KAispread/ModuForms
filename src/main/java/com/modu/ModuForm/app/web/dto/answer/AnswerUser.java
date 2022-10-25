package com.modu.ModuForm.app.web.dto.answer;

import com.modu.ModuForm.app.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AnswerUser {
    private String answer;
    private User user;
    private Boolean anonymousFlag;

    @Builder
    public AnswerUser(String answer, User user, Boolean anonymousFlag) {
        this.answer = answer;
        this.user = user;
        this.anonymousFlag = anonymousFlag;
    }
}

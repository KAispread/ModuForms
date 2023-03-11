package com.modu.ModuForm.app.web.dto.answer;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AnswerResponse {
    private Long answerId;
    private AnswerCheck answerCheck;
    private Boolean anonymousFlag;

    @Builder
    public AnswerResponse(Long answerId, AnswerCheck answerCheck, Boolean anonymousFlag) {
        this.answerId = answerId;
        this.answerCheck = answerCheck;
        this.anonymousFlag = anonymousFlag;
    }


    public AnswerResponse(Long answerId, Boolean anonymousFlag) {
        this.answerId = answerId;
        this.anonymousFlag = anonymousFlag;
    }

    public void setAnswerCheck(AnswerCheck answerCheck) {
        this.answerCheck = answerCheck;
    }
}

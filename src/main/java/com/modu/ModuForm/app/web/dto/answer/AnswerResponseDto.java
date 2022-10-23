package com.modu.ModuForm.app.web.dto.answer;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AnswerResponseDto {
    private Long answerId;
    private AnswerCheckDto answerCheckDto;
    private Boolean anonymousFlag;

    @Builder
    public AnswerResponseDto(Long answerId, AnswerCheckDto answerCheckDto, Boolean anonymousFlag) {
        this.answerId = answerId;
        this.answerCheckDto = answerCheckDto;
        this.anonymousFlag = anonymousFlag;
    }
}

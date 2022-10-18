package com.modu.ModuForm.app.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AnswerPreview {
    private final String surveyTitle;
    private final String description;
    private final LocalDateTime answerDate;
    private final LocalDateTime modifiedDate;

    @Builder
    public AnswerPreview(String surveyTitle, String description, LocalDateTime answerDate, LocalDateTime modifiedDate) {
        this.surveyTitle = surveyTitle;
        this.description = description;
        this.answerDate = answerDate;
        this.modifiedDate = modifiedDate;
    }

}

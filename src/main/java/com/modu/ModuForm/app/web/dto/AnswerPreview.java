package com.modu.ModuForm.app.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AnswerPreview {
    private String surveyTitle;
    private String description;
    private LocalDateTime answerDate;

    @Builder
    public AnswerPreview(String surveyTitle, String description, LocalDateTime answerDate) {
        this.surveyTitle = surveyTitle;
        this.description = description;
        this.answerDate = answerDate;
    }
}

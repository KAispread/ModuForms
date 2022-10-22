package com.modu.ModuForm.app.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AnswerPreview {
    private final Long id;
    private final String surveyTitle;
    private final String description;
    private final LocalDateTime answerDate;
    private final LocalDateTime modifiedDate;
    private final LocalDateTime surveyEndDate;

    @Builder
    public AnswerPreview(Long id, String surveyTitle, String description, LocalDateTime answerDate, LocalDateTime modifiedDate, LocalDateTime surveyEndDate) {
        this.id = id;
        this.surveyTitle = surveyTitle;
        this.description = description;
        this.answerDate = answerDate;
        this.modifiedDate = modifiedDate;
        this.surveyEndDate = surveyEndDate;
    }
}

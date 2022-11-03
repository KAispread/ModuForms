package com.modu.ModuForm.app.web.dto;

import com.modu.ModuForm.app.domain.surbay.answer.Answer;
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
    public AnswerPreview(Answer answer) {
        this.id = answer.getId();
        this.surveyTitle = answer.getSurvey().getTitle();
        this.description = answer.getSurvey().getDescription();
        this.surveyEndDate = answer.getSurvey().getDeadLine();
        this.answerDate = answer.getCreatedDate();
        this.modifiedDate = answer.getModifiedDate();
    }
}

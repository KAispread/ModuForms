package com.modu.ModuForm.app.web.dto;

import com.modu.ModuForm.app.domain.surbay.Survey;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SurveyPreview {
    private final String author;
    private final Long id;
    private final String title;
    private final String description;
    private final LocalDateTime deadLine;
    private final LocalDateTime createdDate;
    private final Integer answersCount;

    @Builder
    public SurveyPreview(Survey survey) {
        this.author = survey.getUsers().getNickName();
        this.id = survey.getId();
        this.title = survey.getTitle();
        this.description = survey.getDescription();
        this.deadLine = survey.getDeadLine();
        this.createdDate = survey.getCreatedDate();
        this.answersCount = setAnswerCount(survey);
    }

    private boolean hasNoAnswer(Survey survey) {
        return survey.getAnswers() == null;
    }

    private int setAnswerCount(Survey survey) {
        if (hasNoAnswer(survey)) return 0;
        return survey.getAnswers().size();
    }
}

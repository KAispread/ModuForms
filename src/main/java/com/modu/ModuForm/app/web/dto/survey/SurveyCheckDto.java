package com.modu.ModuForm.app.web.dto.survey;

import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.surbay.SurveyQuestion;
import com.modu.ModuForm.app.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class SurveyCheckDto {
    private User user;
    private String title;
    private String description;
    private LocalDateTime deadLine;
    private String email;
    private Integer maximumAnswer;
    private List<SurveyQuestionCheck> surveyQuestionList = new ArrayList<>();

    @Builder
    public SurveyCheckDto(Survey survey) {
        this.title = survey.getTitle();
        this.description = survey.getDescription();
        this.deadLine = survey.getDeadLine();
        this.email = survey.getEmail();
        this.maximumAnswer = survey.getMaximumAnswer();
        this.user = survey.getUser();
        convertSurveyQuestionCheck(survey.getSurveyQuestionList());
    }

    public void convertSurveyQuestionCheck(List<SurveyQuestion> surveyQuestions) {
        surveyQuestionList = surveyQuestions.stream()
                .map(SurveyQuestionCheck::new)
                .collect(Collectors.toList());
    }
}

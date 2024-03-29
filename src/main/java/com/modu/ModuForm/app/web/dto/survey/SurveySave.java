package com.modu.ModuForm.app.web.dto.survey;

import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.surbay.SurveyQuestion;
import com.modu.ModuForm.app.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class SurveySave {
    @Length(min = 2, max = 30, message = "제목은 2자 이상, 30자 미만으로 입력하세요.")
    private String title;
    @Length(min = 2, message = "설명은 2자 이상으로 입력하세요.")
    private String description;
    private String deadLine;
    @Email
    private String email;
    private Integer maximumAnswer;
    @NotEmpty
    private List<SurveyQuestion> surveyQuestionList = new ArrayList<>();
    private int[] DATE_TIME = new int[5];

    @Builder
    public SurveySave(String title, String description, String deadLine, String email, Integer maximumAnswer, List<SurveyQuestion> surveyQuestionList) {
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
        this.email = email;
        this.maximumAnswer = maximumAnswer;
        this.surveyQuestionList = surveyQuestionList;
    }

    public Survey toSurveyEntity(User user) {
        Survey survey = Survey.builder()
                .title(title)
                .description(description)
                .email(email)
                .deadLine(getDeadLineLocalDateTime())
                .surveyQuestionList(surveyQuestionList)
                .maximumAnswer(maximumAnswer)
                .build();
        survey.setUsers(user);
        return survey;
    }

    public LocalDateTime getDeadLineLocalDateTime() {
        if (this.deadLine == null || this.deadLine.equals("-") || this.deadLine.length() == 0) {
            return null;
        }
        String[] split = this.deadLine.split("-|:");
        for(int i = 0; i < 5; i++) {
            DATE_TIME[i] = Integer.parseInt(split[i]);
        }
        return LocalDateTime.of(DATE_TIME[0], DATE_TIME[1],DATE_TIME[2], DATE_TIME[3], DATE_TIME[4]);
    }
}

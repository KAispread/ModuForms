package com.modu.ModuForm.app.web.dto.answer;

import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.surbay.SurveyQuestion;
import com.modu.ModuForm.app.domain.surbay.answer.AnswerData;
import com.modu.ModuForm.app.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class AnswerCheck {
    private User surveyUser;
    private String title;
    private String description;
    private LocalDateTime deadLine;
    private String email;
    private Integer maximumAnswer;
    private final List<AnswerQuestionCheck> answerQuestions = new ArrayList<>();

    @Builder
    public AnswerCheck(Survey survey, List<AnswerData> answerDataList) {
        this.title = survey.getTitle();
        this.description = survey.getDescription();
        this.deadLine = survey.getDeadLine();
        this.email = survey.getEmail();
        this.maximumAnswer = survey.getMaximumAnswer();
        this.surveyUser = survey.getUser();
        convertAnswerQuestionCheck(survey.getSurveyQuestionList(), answerDataList);
    }

    public void convertAnswerQuestionCheck(List<SurveyQuestion> questions, List<AnswerData> answerDataList) {
        for (int i = 0; i < questions.size(); i++) {
            answerQuestions.add(new AnswerQuestionCheck(questions.get(i), answerDataList.get(i).getAnswer()));
        }
    }
}

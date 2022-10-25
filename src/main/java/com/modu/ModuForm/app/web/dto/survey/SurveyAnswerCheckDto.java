package com.modu.ModuForm.app.web.dto.survey;

import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.surbay.SurveyQuestion;
import com.modu.ModuForm.app.domain.surbay.answer.Answer;
import com.modu.ModuForm.app.web.dto.answer.QuestionListForSurveyAnswer;
import com.modu.ModuForm.app.web.dto.user.UserDetailsForAnswer;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SurveyAnswerCheckDto {
    private final Long surveyId;
    private final String surveyTitle;
    private final int answerCount;
    private final List<UserDetailsForAnswer> userList = new ArrayList<>();
    private final List<QuestionListForSurveyAnswer> questionList = new ArrayList<>();

    public SurveyAnswerCheckDto(Survey survey, List<Answer> answerList) {
        this.surveyId = survey.getId();
        this.surveyTitle = survey.getTitle();
        this.answerCount = answerList.size();
        setUserList(answerList);
        setQuestionList(survey.getSurveyQuestionList(), answerList);
    }

    public void setQuestionList(List<SurveyQuestion> surveyQuestionList, List<Answer> answerList) {
        for (int i = 0; i< surveyQuestionList.size(); i++) {
            questionList.add(new QuestionListForSurveyAnswer(surveyQuestionList.get(i), answerList, i));
        }
    }

    public void setUserList(List<Answer> answerList) {
        for (Answer answer : answerList) {
            this.userList.add(UserDetailsForAnswer.builder()
                    .userNickname(answer.getUser().getNickName())
                    .email(answer.getUser().getEmail())
                    .anonymousFlag(answer.getAnonymousFlag())
                    .build());
        }
    }
}

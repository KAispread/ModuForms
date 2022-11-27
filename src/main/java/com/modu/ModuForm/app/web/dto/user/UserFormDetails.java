package com.modu.ModuForm.app.web.dto.user;

import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.surbay.answer.Answer;
import com.modu.ModuForm.app.domain.user.Role;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.web.dto.answer.AnswerPage;
import com.modu.ModuForm.app.web.dto.survey.SurveyPage;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class UserFormDetails {
    private final Long id;
    private final String name;
    private final String nickName;
    private final Role role;
    private final SurveyPage surveyPage;
    private final AnswerPage answerPage;

    public UserFormDetails(User user, Page<Survey> surveyPageList, Integer currentSurveyPage, Page<Answer> answerPageList, Integer currentAnswerPage) {
        this.id = user.getId();
        this.name = user.getName();
        this.role = user.getRole();
        this.nickName = user.getNickName();
        this.surveyPage = new SurveyPage(surveyPageList, currentSurveyPage);
        this.answerPage = new AnswerPage(answerPageList, currentAnswerPage);
    }
}

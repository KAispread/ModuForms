package com.modu.ModuForm.app.web.dto.user;

import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.surbay.answer.Answer;
import com.modu.ModuForm.app.domain.user.Role;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.web.dto.AnswerPreview;
import com.modu.ModuForm.app.web.dto.SurveyPreview;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
public class UserFormDetailsDto {
    private final Long id;
    private final String name;
    private final String nickName;
    private final Role role;
    private List<AnswerPreview> answerPreviewList = new ArrayList<>();
    private List<SurveyPreview> surveyPreviewList = new ArrayList<>();

    public UserFormDetailsDto(User user, List<Answer> answerList, List<Survey> surveyList) {
        this.id = user.getId();
        this.name = user.getName();
        this.role = user.getRole();
        this.nickName = user.getNickName();
        createAnswerPreview(answerList);
        createSurveyPreview(surveyList);
    }

    public void createAnswerPreview(List<Answer> answerList) {
        for (Answer answer : answerList) {
            answerPreviewList.add(AnswerPreview.builder()
                    .surveyTitle(answer.getSurvey().getTitle())
                    .description(answer.getSurvey().getDescription())
                    .answerDate(answer.getCreatedDate())
                    .modifiedDate(answer.getModifiedDate())
                    .build());
        }
    }

    public void createSurveyPreview(List<Survey> surveyList) {
        for (Survey survey : surveyList) {
            surveyPreviewList.add(new SurveyPreview(survey));
        }
    }
}

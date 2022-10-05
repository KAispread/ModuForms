package com.modu.ModuForm.app.web.dto.user;

import com.modu.ModuForm.app.domain.surbay.answer.Answer;
import com.modu.ModuForm.app.domain.user.Role;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.web.dto.AnswerPreview;
import lombok.Getter;

import java.util.List;

@Getter
public class UserSubDetailsDto {
    private final Long id;
    private final String name;
    private final Role role;
    private List<AnswerPreview> answerPreviewList;

    public UserSubDetailsDto(User user, List<Answer> answerList) {
        this.id = user.getId();
        this.name = user.getName();
        this.role = user.getRole();
        createAnswerPreview(answerList);
    }

    public void createAnswerPreview(List<Answer> answerList) {
        for (Answer answer : answerList) {
            answerPreviewList.add(AnswerPreview.builder()
                    .surveyTitle(answer.getSurvey().getTitle())
                    .description(answer.getSurvey().getDescription())
                    .answerDate(answer.getAnswerDate())
                    .build());
        }
    }
}

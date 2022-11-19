package com.modu.ModuForm.app.web.dto.user;

import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.user.User;
import lombok.Getter;

import java.util.List;

@Getter
public class UserDetailsDto {
    private final Long id;
    private final String name;
    private final List<Survey> surveyList;

    public UserDetailsDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surveyList = user.getSurveyList();
    }
}

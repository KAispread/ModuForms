package com.modu.ModuForm.app.web.dto.user;

import com.modu.ModuForm.app.domain.surbay.answer.Answer;
import com.modu.ModuForm.app.domain.user.Access;

import java.util.List;

public class UserDetailsDto {
    private Long id;
    private String name;
    private List<Answer> answerList;
}

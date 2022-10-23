package com.modu.ModuForm.app.web.dto.survey.questype;

import com.modu.ModuForm.app.domain.surbay.answer.Answer;
import com.modu.ModuForm.app.web.dto.answer.AnswerUser;
import com.modu.ModuForm.app.web.dto.survey.DistractorCount;

import java.util.List;

public interface QuesTypeDtoGenerator {
    public List<AnswerUser> getAnswerUserList(List<DistractorCount> distractor, List<Answer> answerList, int index);
}

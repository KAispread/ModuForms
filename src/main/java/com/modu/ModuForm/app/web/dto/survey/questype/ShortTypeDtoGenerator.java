package com.modu.ModuForm.app.web.dto.survey.questype;

import com.modu.ModuForm.app.domain.surbay.answer.Answer;
import com.modu.ModuForm.app.web.dto.answer.AnswerUser;
import com.modu.ModuForm.app.web.dto.survey.DistractorCount;

import java.util.ArrayList;
import java.util.List;

public class ShortTypeDtoGenerator implements QuesTypeDtoGenerator {
    @Override
    public List<AnswerUser> getAnswerUserList(List<DistractorCount> distractor, List<Answer> answerList, int index) {
        List<AnswerUser> answerUser = new ArrayList<>();
        for (Answer answer : answerList) {
            answerUser.add(AnswerUser.builder()
                    .user(answer.getUser())
                    .answer(answer.getAnswerDataList().get(index).getAnswer())
                    .build());
        }
        return answerUser;
    }
}

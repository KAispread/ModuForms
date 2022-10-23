package com.modu.ModuForm.app.web.dto.survey.questype;

import com.modu.ModuForm.app.domain.surbay.answer.Answer;
import com.modu.ModuForm.app.web.dto.answer.AnswerUser;
import com.modu.ModuForm.app.web.dto.survey.DistractorCount;

import java.util.ArrayList;
import java.util.List;

public class MultipleTypeDtoGenerator implements QuesTypeDtoGenerator{
    @Override
    public List<AnswerUser> getAnswerUserList(List<DistractorCount> distractor, List<Answer> answerList, int index) {
        List<AnswerUser> answerUser = new ArrayList<>();
        for (Answer answer : answerList) {
            setDistractorCount(distractor, answer, index);
            answerUser.add(AnswerUser.builder()
                    .user(answer.getUser())
                    .answer(answer.getAnswerDataList().get(index).getAnswer())
                    .build());
        }
        return answerUser;
    }

    public void setDistractorCount(List<DistractorCount> distractor, Answer answer, int index) {
        for (DistractorCount distractorCount : distractor) {
            if (distractorCount.getDistractor().equals(answer.getAnswerDataList().get(index).getAnswer())) {
                distractorCount.countAdd();
            }
        }
    }
}

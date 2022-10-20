package com.modu.ModuForm.app.web.dto.survey;

import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.surbay.answer.Answer;
import com.modu.ModuForm.app.domain.surbay.answer.AnswerData;
import com.modu.ModuForm.app.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class AnswerSaveDto {
    private Boolean anonymousFlag;
    private List<String> answerList;

    @Builder
    public AnswerSaveDto(Boolean anonymousFlag, List<String> answerList) {
        this.anonymousFlag = anonymousFlag;
        this.answerList = answerList;
    }


    public Answer toEntity(Survey survey, User user) {
        return Answer.builder()
                .answerDataList(convertAnswerData())
                .user(user)
                .survey(survey)
                .anonymousFlag(anonymousFlag)
                .build();
    }

    public List<AnswerData> convertAnswerData(){
        List<AnswerData> answerData = new ArrayList<>();
        int num = 0;

        for (String ans : this.answerList) {
            answerData.add(AnswerData.builder()
                    .number(num)
                    .answer(ans)
                    .build());
            num += 1;
        }
        return answerData;
    }
}

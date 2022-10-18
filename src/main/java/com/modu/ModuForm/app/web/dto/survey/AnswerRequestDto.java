package com.modu.ModuForm.app.web.dto.survey;

import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.surbay.answer.Answer;
import com.modu.ModuForm.app.domain.surbay.answer.AnswerData;
import com.modu.ModuForm.app.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
public class AnswerRequestDto {
    private Long userId;
    private List<String> answerQuestion;

    @Builder
    public AnswerRequestDto(List<String> answerQuestion, Long userId) {
        this.userId = userId;
        this.answerQuestion = answerQuestion;
    }

    public Answer toEntity(Survey survey, User user) {
        Answer ans = Answer.builder()
                .answerDataList(convertAnswerData(answerQuestion))
                .user(user)
                .build();
        ans.setSurveyRef(survey);
        return ans;
    }

    public List<AnswerData> convertAnswerData(List<String> answerQuestion){
        List<AnswerData> answerData = new ArrayList<>();
        int num = 0;

        for (String ans : answerQuestion) {
            num += 1;
            answerData.add(AnswerData.builder()
                    .number(num)
                    .answer(ans)
                    .build());
        }
        return answerData;
    }
}

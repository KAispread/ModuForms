package com.modu.ModuForm.app.web.dto.answer;

import com.modu.ModuForm.app.domain.surbay.QuesType;
import com.modu.ModuForm.app.domain.surbay.SurveyQuestion;
import com.modu.ModuForm.app.domain.surbay.answer.Answer;
import com.modu.ModuForm.app.web.dto.survey.DistractorCount;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class QuestionListForSurveyAnswer {
    private Integer number;
    private String question;
    private List<AnswerUser> answerUser;
    private final List<DistractorCount> distractor = new ArrayList<>();
    private QuesType questionType;

    public QuestionListForSurveyAnswer(SurveyQuestion surveyQuestion, List<Answer> answerList, int index) {
        this.number = surveyQuestion.getNumber();
        this.question = surveyQuestion.getQuestion();
        this.questionType = surveyQuestion.getQuestionType();
        this.answerUser = getAnswerUserList(answerList, index);
        setDistractor(surveyQuestion, answerList, index);
    }

    public void setDistractor(SurveyQuestion surveyQuestion, List<Answer> answerList, int index) {
        if (surveyQuestion.getQuestionType() != QuesType.SHORT && surveyQuestion.getDistractor() != null) {
            String[] distractorArray = surveyQuestion.getDistractor().split("\\|");
            for (String dist : distractorArray) {
                int count = 0;
                for (Answer answer : answerList) {
                    if (dist.equals(answer.getAnswerDataList().get(index).getResponse())) {
                        count++;
                    }
                }
                distractor.add(new DistractorCount(dist, count, answerList.size()));
            }
        }
    }

    public List<AnswerUser> getAnswerUserList(List<Answer> answerList, int index) {
        List<AnswerUser> answerUser = new ArrayList<>();
        for (Answer answer : answerList) {
            answerUser.add(AnswerUser.builder()
                    .user(answer.getUsers())
                    .answer(answer.getAnswerDataList().get(index).getResponse())
                    .anonymousFlag(answer.getAnonymousFlag())
                    .build());
        }
        return answerUser;
    }
}

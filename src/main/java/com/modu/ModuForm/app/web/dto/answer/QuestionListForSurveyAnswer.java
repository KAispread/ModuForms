package com.modu.ModuForm.app.web.dto.answer;

import com.modu.ModuForm.app.domain.surbay.QuesType;
import com.modu.ModuForm.app.domain.surbay.SurveyQuestion;
import com.modu.ModuForm.app.domain.surbay.answer.Answer;
import com.modu.ModuForm.app.web.dto.survey.DistractorCount;
import com.modu.ModuForm.app.web.dto.survey.questype.MultipleTypeDtoGenerator;
import com.modu.ModuForm.app.web.dto.survey.questype.QuesTypeDtoGenerator;
import com.modu.ModuForm.app.web.dto.survey.questype.ShortTypeDtoGenerator;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class QuestionListForSurveyAnswer {
    private Integer number;
    private String question;
    private final List<AnswerUser> answerUser;
    private final List<DistractorCount> distractor = new ArrayList<>();
    private QuesType questionType;
    private QuesTypeDtoGenerator quesTypeDtoGenerator;

    public QuestionListForSurveyAnswer(SurveyQuestion surveyQuestion, List<Answer> answerList, int index) {
        this.number = surveyQuestion.getNumber();
        this.question = surveyQuestion.getQuestion();
        this.questionType = surveyQuestion.getQuestionType();
        this.quesTypeDtoGenerator = setQuesTypeDtoGenerator();
        this.answerUser = convertToAnswerUser(answerList, index);
        setDistractor(surveyQuestion);
    }

    public void setDistractor(SurveyQuestion surveyQuestion) {
        if (surveyQuestion.getQuestionType() != QuesType.SHORT && surveyQuestion.getDistractor() != null) {
            String[] distractorArray = surveyQuestion.getDistractor().split("\\|");
            for (String dist : distractorArray) {
                distractor.add(new DistractorCount(dist));
            }
        }
    }

    public List<AnswerUser> convertToAnswerUser(List<Answer> answerList, int index) {
        return quesTypeDtoGenerator.getAnswerUserList(this.distractor, answerList, index);
    }

    private QuesTypeDtoGenerator setQuesTypeDtoGenerator() {
        if (questionType == QuesType.SHORT) {
            return new ShortTypeDtoGenerator();
        } else if (questionType == QuesType.MULTIPLE) {
            return new MultipleTypeDtoGenerator();
        }
        return null;
    }
}

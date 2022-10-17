package com.modu.ModuForm.app.web.dto.survey;


import com.modu.ModuForm.app.domain.surbay.QuesType;
import com.modu.ModuForm.app.domain.surbay.SurveyQuestion;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class SurveyQuestionCheck {
    private Integer number;
    private String question;
    private List<String> distractor = new ArrayList<>();
    private QuesType questionType;

    public SurveyQuestionCheck(SurveyQuestion surveyQuestion) {
        this.number = surveyQuestion.getNumber();
        this.question = surveyQuestion.getQuestion();
        this.questionType = surveyQuestion.getQuestionType();
        setDistractor(surveyQuestion);
    }

    public void setDistractor(SurveyQuestion surveyQuestion) {
        if (surveyQuestion.getQuestionType() != QuesType.SHORT && surveyQuestion.getDistractor() != null) {
            String[] distractorArray = surveyQuestion.getDistractor().split("\\|");
            for (String dist : distractorArray) {
                distractor.add(dist);
                System.out.println("distactor = " + dist);
            }
        }
    }
}

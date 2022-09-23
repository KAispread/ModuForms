package com.modu.ModuForm.app.domain.surbay;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Embeddable
public class SurveyQuestion {
    private int number;
    private String question;

    @Builder
    public SurveyQuestion(int number, String question) {
        this.number = number;
        this.question = question;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SurveyQuestion that = (SurveyQuestion) o;
        return number == that.number && question.equals(that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, question);
    }
}

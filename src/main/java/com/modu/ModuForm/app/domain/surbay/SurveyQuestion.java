package com.modu.ModuForm.app.domain.surbay;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class SurveyQuestion {
    private Integer number;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String question;
    @Lob
    private String distractor;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuesType questionType;

    @Builder
    public SurveyQuestion(Integer number, String question, String distractor, QuesType questionType) {
        this.number = number;
        this.question = question;
        this.distractor = distractor;
        this.questionType = questionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SurveyQuestion that = (SurveyQuestion) o;
        return number.equals(that.number) && question.equals(that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, question);
    }
}

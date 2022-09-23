package com.modu.ModuForm.app.domain.surbay.answer;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Embeddable
public class AnswerData {
   private int number;
   @Column(columnDefinition = "TEXT")
   private String question;
   @Column(columnDefinition = "TEXT", nullable = false)
   private String answer;

   @Builder
    public AnswerData(int number, String question, String answer) {
        this.number = number;
        this.question = question;
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerData that = (AnswerData) o;
        return getNumber() == that.getNumber() && Objects.equals(getQuestion(), that.getQuestion()) && Objects.equals(getAnswer(), that.getAnswer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getQuestion(), getAnswer());
    }
}
package com.modu.ModuForm.app.domain.surbay.answer;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class AnswerData {
    @Column(nullable = false)
   private int number;
   @Column(columnDefinition = "TEXT", nullable = false)
   private String answer;

   @Builder
    public AnswerData(int number, String answer) {
        this.number = number;
        this.answer = answer;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerData that = (AnswerData) o;
        return getNumber() == that.getNumber() && Objects.equals(getAnswer(), that.getAnswer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getAnswer());
    }
}
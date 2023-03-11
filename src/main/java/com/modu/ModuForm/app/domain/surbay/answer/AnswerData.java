package com.modu.ModuForm.app.domain.surbay.answer;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@Table(name = "ANSWER_DATA")
public class AnswerData {
    @Column(nullable = false)
   private int number;
   @Column(columnDefinition = "TEXT", nullable = false)
   private String response;

   @Builder
    public AnswerData(int number, String response) {
        this.number = number;
        this.response = response;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerData that = (AnswerData) o;
        return getNumber() == that.getNumber() && Objects.equals(getResponse(), that.getResponse());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getResponse());
    }
}
package com.modu.ModuForm.app.domain.surbay.answer;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Entity
public class Answer {
    @Column(name = "ANSWER_ID")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SURVEY_ANSWER_ID")
    private SurveyAnswer surveyAnswer;

    private LocalDateTime answerDate;

    @ElementCollection
    @CollectionTable(name = "ANSWER_DATA",
            joinColumns = @JoinColumn(name = "ANSWER_ID")
    )
    private List<AnswerData> answerDataList = new ArrayList<>();

    public void setAnswerDataList(List<AnswerData> answerDataList) {
        this.answerDataList = answerDataList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(getId(), answer.getId()) && Objects.equals(getSurveyAnswer(), answer.getSurveyAnswer()) && Objects.equals(getAnswerDate(), answer.getAnswerDate()) && Objects.equals(getAnswerDataList(), answer.getAnswerDataList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSurveyAnswer(), getAnswerDate(), getAnswerDataList());
    }
}

package com.modu.ModuForm.app.domain.surbay;

import com.modu.ModuForm.app.domain.surbay.answer.Answer;
import com.modu.ModuForm.app.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Entity
public class SurveyAnswer {
    @Column(name = "SURVEY_ANSWER_ID")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SURVEY_ID", nullable = false)
    private Survey survey;

    @OneToMany(mappedBy = "surveyAnswer", cascade = CascadeType.ALL)
    private List<Answer> answer;

    private Long count;

    @Builder
    public SurveyAnswer(User user, Survey survey, Long count) {
        this.user = user;
        this.survey = survey;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SurveyAnswer that = (SurveyAnswer) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getUser(), that.getUser()) && Objects.equals(getSurvey(), that.getSurvey()) && Objects.equals(getAnswer(), that.getAnswer()) && Objects.equals(getCount(), that.getCount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getSurvey(), getAnswer(), getCount());
    }
}

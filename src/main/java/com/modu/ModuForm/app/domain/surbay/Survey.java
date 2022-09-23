package com.modu.ModuForm.app.domain.surbay;

import com.modu.ModuForm.app.domain.user.admin.Admin;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@NoArgsConstructor
@Entity
public class Survey {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SURVEY_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADMIN_ID", nullable = false)
    private Admin admin;

    @OneToMany(mappedBy = "survey")
    private List<SurveyAnswer> surveyAnswer;

    @ElementCollection
    @CollectionTable(name = "SURVEY_QUESTION", joinColumns =
        @JoinColumn(name = "SURVEY_ID")
    )
    private List<SurveyQuestion> surveyQuestionList = new ArrayList<>();

    @Column(name = "POSTDATE", nullable = false)
    private LocalDateTime postDate;

    @Column(name = "DEADLINE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deadLine;

    @Column(name = "MAXIMUM_ANSWER")
    private Long maximumAnswer;

    @Builder
    public Survey(Admin admin, LocalDateTime postDate, Date deadLine, Long maximumAnswer) {
        this.admin = admin;
        this.postDate = postDate;
        this.deadLine = deadLine;
        this.maximumAnswer = maximumAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Survey survey = (Survey) o;
        return Objects.equals(getId(), survey.getId()) && Objects.equals(getAdmin(), survey.getAdmin()) && Objects.equals(getSurveyAnswer(), survey.getSurveyAnswer()) && Objects.equals(getSurveyQuestionList(), survey.getSurveyQuestionList()) && Objects.equals(getPostDate(), survey.getPostDate()) && Objects.equals(getDeadLine(), survey.getDeadLine()) && Objects.equals(getMaximumAnswer(), survey.getMaximumAnswer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAdmin(), getSurveyAnswer(), getSurveyQuestionList(), getPostDate(), getDeadLine(), getMaximumAnswer());
    }
}

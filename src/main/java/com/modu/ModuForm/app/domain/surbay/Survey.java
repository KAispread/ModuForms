package com.modu.ModuForm.app.domain.surbay;

import com.modu.ModuForm.app.domain.BaseTimeEntity;
import com.modu.ModuForm.app.domain.surbay.answer.Answer;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.web.dto.survey.SurveySave;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Survey extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SURVEY_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User users;

    @OneToMany(mappedBy = "survey", orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

    @Column(nullable = false)
    private String title;

    private String email;

    private String description;

    @ElementCollection
    @CollectionTable(name = "SURVEY_QUESTION", joinColumns =
        @JoinColumn(name = "SURVEY_ID")
    )
    private List<SurveyQuestion> surveyQuestionList = new ArrayList<>();

    @Column(name = "DEADLINE")
    private LocalDateTime deadLine;

    @Column(name = "MAXIMUM_ANSWER")
    private Integer maximumAnswer;

    @Builder
    public Survey(User users, String email, List<Answer> answers, String title, String description, List<SurveyQuestion> surveyQuestionList, LocalDateTime deadLine, Integer maximumAnswer) {
        this.users = users;
        this.answers = answers;
        this.title = title;
        this.description = description;
        this.surveyQuestionList = surveyQuestionList;
        this.deadLine = deadLine;
        this.email = email;
        this.maximumAnswer = maximumAnswer;
    }

    public void setUsers(User users) {
        this.users = users;
        if (!users.getSurveyList().contains(this)) {
            users.getSurveyList().add(this);
        }
    }

    public void addAnswer(Answer answer) {
        if (!answers.contains(answer)) this.answers.add(answer);
    }

    public void update(SurveySave surveySave) {
        this.answers.clear();
        this.title = surveySave.getTitle();
        this.description = surveySave.getDescription();
        this.email = surveySave.getEmail();
        this.maximumAnswer = surveySave.getMaximumAnswer();
        this.surveyQuestionList.clear();
        this.surveyQuestionList = surveySave.getSurveyQuestionList();
        this.deadLine = surveySave.getDeadLineLocalDateTime();
    };

    public void updateQuestion(List<SurveyQuestion> newSurveyQuestionList) {
        this.surveyQuestionList.clear();
        this.surveyQuestionList = newSurveyQuestionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Survey survey = (Survey) o;
        return Objects.equals(getId(), survey.getId()) && Objects.equals(getUsers(), survey.getUsers()) && Objects.equals(getAnswers(), survey.getAnswers()) && Objects.equals(getTitle(), survey.getTitle()) && Objects.equals(getEmail(), survey.getEmail()) && Objects.equals(getDescription(), survey.getDescription()) && Objects.equals(getSurveyQuestionList(), survey.getSurveyQuestionList()) && Objects.equals(getDeadLine(), survey.getDeadLine()) && Objects.equals(getMaximumAnswer(), survey.getMaximumAnswer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsers(), getAnswers(), getTitle(), getEmail(), getDescription(), getSurveyQuestionList(), getDeadLine(), getMaximumAnswer());
    }
}

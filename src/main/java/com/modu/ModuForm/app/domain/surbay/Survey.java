package com.modu.ModuForm.app.domain.surbay;

import com.modu.ModuForm.app.domain.surbay.answer.Answer;
import com.modu.ModuForm.app.domain.user.User;
import lombok.Builder;
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
public class Survey {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SURVEY_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @OneToMany(mappedBy = "survey")
    private List<Answer> answers = new ArrayList<>();

    @Column(nullable = false)
    private String title;

    private String description;

    @ElementCollection
    @CollectionTable(name = "SURVEY_QUESTION", joinColumns =
        @JoinColumn(name = "SURVEY_ID")
    )
    private List<SurveyQuestion> surveyQuestionList = new ArrayList<>();

    @Column(name = "POSTDATE", nullable = false)
    private LocalDateTime postDate;

    @Column(name = "DEADLINE")
    private LocalDateTime deadLine;

    @Column(name = "MAXIMUM_ANSWER")
    private int maximumAnswer;

    @Builder
    public Survey(User user, List<Answer> answers, String title, String description, List<SurveyQuestion> surveyQuestionList, LocalDateTime postDate, LocalDateTime deadLine, int maximumAnswer) {
        this.user = user;
        this.answers = answers;
        this.title = title;
        this.description = description;
        this.surveyQuestionList = surveyQuestionList;
        this.postDate = postDate;
        this.deadLine = deadLine;
        this.maximumAnswer = maximumAnswer;
    }

    public void setUser(User user) {
        this.user = user;
        user.setSurveyList(this);
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public void updateQuestion(List<SurveyQuestion> newSurveyQuestionList) {
        surveyQuestionList = newSurveyQuestionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Survey survey = (Survey) o;
        return getMaximumAnswer() == survey.getMaximumAnswer() && Objects.equals(getId(), survey.getId()) && Objects.equals(getUser(), survey.getUser()) && Objects.equals(getAnswers(), survey.getAnswers()) && Objects.equals(getTitle(), survey.getTitle()) && Objects.equals(getDescription(), survey.getDescription()) && Objects.equals(getSurveyQuestionList(), survey.getSurveyQuestionList()) && Objects.equals(getPostDate(), survey.getPostDate()) && Objects.equals(getDeadLine(), survey.getDeadLine());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getAnswers(), getTitle(), getDescription(), getSurveyQuestionList(), getPostDate(), getDeadLine(), getMaximumAnswer());
    }
}

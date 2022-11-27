package com.modu.ModuForm.app.domain.surbay.answer;

import com.modu.ModuForm.app.domain.BaseTimeEntity;
import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.web.dto.answer.AnswerSave;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Answer extends BaseTimeEntity {
    @Column(name = "ANSWER_ID")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SURVEY_ID")
    private Survey survey;

    @ElementCollection
    @CollectionTable(name = "ANSWER_DATA",
            joinColumns = @JoinColumn(name = "ANSWER_ID")
    )
    private List<AnswerData> answerDataList = new ArrayList<>();

    @Column(nullable = false)
    private Boolean anonymousFlag;

    @Builder
    public Answer(User user, Survey survey, List<AnswerData> answerDataList, Boolean anonymousFlag) {
        validateUser(user, survey);
        this.user = user;
        this.survey = survey;
        this.answerDataList = answerDataList;
        this.anonymousFlag = anonymousFlag;
    }

    public void setSurvey(Survey survey) {
        validateUser(survey);
        this.survey = survey;
        survey.addAnswer(this);
    }

    public void update(AnswerSave answerSave) {
        this.anonymousFlag = answerSave.getAnonymousFlag();
        this.answerDataList.clear();
        this.answerDataList = answerSave.convertAnswerData();
    }

    private void validateUser(Survey survey) {
        if (this.user.equals(survey.getUser())) {
            throw new IllegalArgumentException("설문 등록자는 본인 설문에 응답할 수 없습니다");
        }
    }

    private void validateUser(User user, Survey survey) {
        if (user.equals(survey.getUser())) {
            throw new IllegalArgumentException("설문 등록자는 본인 설문에 응답할 수 없습니다");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(getId(), answer.getId()) && Objects.equals(getUser(), answer.getUser()) && Objects.equals(getSurvey(), answer.getSurvey()) && Objects.equals(getAnswerDataList(), answer.getAnswerDataList()) && Objects.equals(getAnonymousFlag(), answer.getAnonymousFlag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getSurvey(), getAnswerDataList(), getAnonymousFlag());
    }
}

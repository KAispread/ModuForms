package com.modu.ModuForm.app.domain.surbay;

import com.modu.ModuForm.app.DummyDataInit;
import com.modu.ModuForm.app.domain.user.Gender;
import com.modu.ModuForm.app.domain.user.Role;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SurveyRepositoryTest {
    @Autowired
    SurveyRepository surveyRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DummyDataInit dummyData;

    @BeforeEach
    void createDummyData() {
        //given
        User user = dummyData.userInit();
        List<SurveyQuestion> surveyQuestionList = dummyData.surveyQuestionInit();
        dummyData.surveyInit(user, surveyQuestionList);
    }

    @AfterEach
    public void cleanUp(){
        surveyRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName(value = "설문이_등록된다.")
    @Transactional
    public void 설문이_등록된다() {
        //when
        User user = userRepository.findByNickName("Kai").orElseThrow();
        Survey survey = surveyRepository.findSurveysByUser(user).get(0);

        //then
        assertThat(survey.getMaximumAnswer()).isEqualTo(200);
        assertThat(survey.getTitle()).isEqualTo("참여 조사");
    }

    @Test
    @DisplayName(value = "설문이 수정된다.")
    public void 설문이_수정된다(){
        //given
        // 질문 수정
        List<SurveyQuestion> newSurveyQuestions = new ArrayList<>();
        newSurveyQuestions.add(dummyData.buildQuestion(0, "hello", null,QuesType.SHORT));
        newSurveyQuestions.add(dummyData.buildQuestion(1, "주소를 입력해주세요", null, QuesType.SHORT));

        //when
        User user = userRepository.findByNickName("Kai").orElseThrow();
        Survey survey = surveyRepository.findSurveysByUser(user).get(0);

        survey.updateQuestion(newSurveyQuestions);

        Optional<Survey> updatedSurvey = surveyRepository.findById(survey.getId());
        //then
        assertThat(updatedSurvey.orElseThrow().getSurveyQuestionList().get(0).getQuestion()).isEqualTo("hello");
    }
    @Test
    @DisplayName(value = "설문이 삭제된다.")
    public void 설문이_삭제된다(){
        //given
        User user = userRepository.findByNickName("Kai").orElseThrow();
        Survey survey = surveyRepository.findSurveysByUser(user).get(0);

        //when
        surveyRepository.delete(survey);

        //then;
        assertThat(surveyRepository.findById(survey.getId())).isEmpty();
    }
}
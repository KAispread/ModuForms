package com.modu.ModuForm.app.domain.surbay;

import com.modu.ModuForm.app.DummyDataInit;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class SurveyRepositoryTest {
    @Autowired
    SurveyRepository surveyRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DummyDataInit dummyData;

    @Test
    @DisplayName(value = "설문이_등록된다.")
    public void post() {
        //given
        User user = dummyData.userInit();
        dummyData.surveyInit();

        //when
        Survey survey = surveyRepository.findSurveysByUser(user).get(0);

        //then
        assertThat(survey.getMaximumAnswer()).isEqualTo(200);
        assertThat(survey.getTitle()).isEqualTo(dummyData.getSURVEY_TITLE());
    }

    @Test
    @DisplayName(value = "설문이 수정된다.")
    public void edit(){
        //given
        User user = dummyData.userInit();
        Survey survey = dummyData.surveyInit();

        // 질문 수정
        List<SurveyQuestion> newSurveyQuestions = new ArrayList<>();
        newSurveyQuestions.add(dummyData.buildQuestion(0, "hello", null,QuesType.SHORT));
        newSurveyQuestions.add(dummyData.buildQuestion(1, "주소를 입력해주세요", null, QuesType.SHORT));

        //when
        survey.updateQuestion(newSurveyQuestions);

        Optional<Survey> updatedSurvey = surveyRepository.findById(survey.getId());
        //then
        assertThat(updatedSurvey.orElseThrow().getSurveyQuestionList().get(0).getQuestion()).isEqualTo("hello");
    }

    @Test
    @DisplayName(value = "설문이 삭제된다.")
    public void delete(){
        //given
        dummyData.userInit();
        Survey survey = dummyData.surveyInit();

        //when
        surveyRepository.delete(survey);

        //then;
        assertThat(surveyRepository.findById(survey.getId())).isEmpty();
    }
}
package com.modu.ModuForm.app.service.survey;

import com.modu.ModuForm.app.DummyDataInit;
import com.modu.ModuForm.app.domain.surbay.SurveyQuestion;
import com.modu.ModuForm.app.domain.surbay.SurveyRepository;
import com.modu.ModuForm.app.domain.surbay.answer.AnswerRepository;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnswerServiceImplTest {
    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnswerServiceImpl answerService;

    @Autowired
    private DummyDataInit dummyData;

    @BeforeEach
    void createDummyData() {
        //given
        User user = dummyData.userInit(userRepository);
        List<SurveyQuestion> surveyQuestionList = dummyData.surveyQuestionInit();
        dummyData.surveyInit(surveyRepository, user, surveyQuestionList);
    }

    @AfterEach
    void cleanUp() {
        surveyRepository.deleteAll();
        userRepository.deleteAll();
        answerRepository.deleteAll();
    }

    @DisplayName("설문 응답이 등록된다.")
    @Test
    @Transactional
    public void AnswerRegist(){

    }
}
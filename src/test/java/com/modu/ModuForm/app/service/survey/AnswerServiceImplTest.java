package com.modu.ModuForm.app.service.survey;

import com.modu.ModuForm.app.DummyDataInit;
import com.modu.ModuForm.app.domain.surbay.QuesType;
import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.surbay.SurveyQuestion;
import com.modu.ModuForm.app.domain.surbay.SurveyRepository;
import com.modu.ModuForm.app.domain.surbay.answer.Answer;
import com.modu.ModuForm.app.domain.surbay.answer.AnswerRepository;
import com.modu.ModuForm.app.domain.user.Gender;
import com.modu.ModuForm.app.domain.user.Role;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
import com.modu.ModuForm.app.web.dto.survey.AnswerRequestDto;
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

import static org.assertj.core.api.Assertions.assertThat;

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
        User user = dummyData.userInit();
        List<SurveyQuestion> surveyQuestionList = dummyData.surveyQuestionInit();
        dummyData.surveyInit(user, surveyQuestionList);
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
        //when
        List<String> answer = new ArrayList<>();
        answer.add("이기우");
        answer.add("25");
        answer.add("경기도");

        User user = userRepository.findByNickName("Kai").orElseThrow();
        Survey survey = surveyRepository.findSurveysByUser(user).get(0);

        AnswerRequestDto answerRequestDto = AnswerRequestDto.builder()
                .userId(user.getId())
                .answerQuestion(answer)
                .build();

        Long answerId = answerService.save(answerRequestDto,
                survey.getId());

        //then
        Answer answerResult = answerRepository.findById(answerId).orElseThrow();
        assertThat(answerResult.getAnswerDataList().get(0).getAnswer()).isEqualTo("이기우");
        assertThat(answerResult.getAnswerDataList().get(1).getAnswer()).isEqualTo("25");
        assertThat(answerResult.getUser().getId()).isEqualTo(user.getId());
    }
}
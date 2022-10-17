package com.modu.ModuForm.app.service.survey;

import com.modu.ModuForm.app.DummyDataInit;
import com.modu.ModuForm.app.domain.surbay.QuesType;
import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.surbay.SurveyQuestion;
import com.modu.ModuForm.app.domain.surbay.SurveyRepository;
import com.modu.ModuForm.app.domain.user.Role;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
import com.modu.ModuForm.app.web.dto.survey.SurveySaveDto;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SurveyServiceImplTest {
    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SurveyServiceImpl surveyService;

    @Autowired
    private DummyDataInit dummyData;

    @AfterEach
    void cleanUp() {
        surveyRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Test
    @Transactional
    public void 설문이_등록() {
        //given
        List<SurveyQuestion> surveyQuestionList = new ArrayList<>();
        surveyQuestionList.add(SurveyQuestion.builder()
                .number(1)
                .question("회식에 참여하십니까?")
                .questionType(QuesType.SHORT)
                .build());
        surveyQuestionList.add(SurveyQuestion.builder()
                .number(2)
                .question("참여자의 성함을 입력해주세요")
                .questionType(QuesType.SHORT)
                .build());

        SurveySaveDto saveDto = SurveySaveDto.builder()
                .title("회식 참여 조사")
                .description("회식 참여 조사를 위한 설문입니다.")
                .deadLine("2022-10-06-15-30")
                .maximumAnswer(200)
                .surveyQuestionList(surveyQuestionList)
                .build();

        Long saved = surveyService.save(saveDto, "Kai");

        //when
        Survey survey = surveyRepository.findById(saved).orElse(null);
        User user = userRepository.findByNickName("Kai").orElse(null);

        //then
        assertThat(survey.getTitle()).isEqualTo("회식 참여 조사");
        assertThat(survey.getSurveyQuestionList().get(0).getQuestion()).isEqualTo("회식에 참여하십니까?");
        assertThat(user.getSurveyList()).contains(survey);
    }
}
package com.modu.ModuForm.app.domain.surbay;

import com.modu.ModuForm.app.domain.user.admin.Admin;
import com.modu.ModuForm.app.domain.user.admin.AdminRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SurveyRepositoryTest {
    @Autowired
    SurveyRepository surveyRepository;
    @Autowired
    AdminRepository adminRepository;

    @AfterEach
    public void cleanUp(){
        surveyRepository.deleteAll();
    }

    @Test
    @DisplayName(value = "설문이_등록된다.")
    @Transactional
    public void surveyRegistration() {
        //given
        // 관리자 생성
        Admin admin = Admin.builder()
                        .user(null)
                        .name("KAI")
                        .phone("2323")
                        .company("Inflearn")
                        .email("asdfa@com")
                        .build();
        adminRepository.save(admin);

        // 질문 추가
        List<SurveyQuestion> surveyQuestions = new ArrayList<>();
        surveyQuestions.add(buildQuestion(1, "이름을 입력해주세요"));
        surveyQuestions.add(buildQuestion(2, "나이를 입력해주세요"));
        surveyQuestions.add(buildQuestion(3, "주소를 입력해주세요"));

        // 설문 양식 생성
        Survey newSurvey = Survey.builder()
                .admin(admin)
                .postDate(LocalDateTime.now())
                .deadLine(LocalDateTime.of(2022, 9, 30, 20, 0))
                .maximumAnswer(200)
                .surveyQuestionList(surveyQuestions)
                .build();

        surveyRepository.save(newSurvey);

        //when
        Optional<Survey> survey = surveyRepository.findById(newSurvey.getId());

        //then;
        assertThat(survey.orElseThrow().getMaximumAnswer()).isEqualTo(200);
        assertThat(survey.orElseThrow().getDeadLine()).isEqualTo(LocalDateTime.of(2022,9,30,20,0));

        assertThat(survey.orElseThrow().getSurveyQuestionList().get(0)).isEqualTo(surveyQuestions.get(0));
        assertThat(survey.orElseThrow().getSurveyQuestionList().get(1)).isEqualTo(surveyQuestions.get(1));
        assertThat(survey.orElseThrow().getSurveyQuestionList().get(2)).isEqualTo(surveyQuestions.get(2));
    }

    @Test
    @DisplayName(value = "설문이 수정된다.")
    @Transactional
    public void updateSurvey(){
        //given
        // 관리자 생성
        Admin admin = Admin.builder()
                .user(null)
                .name("KAI")
                .phone("2323")
                .company("Inflearn")
                .email("asdfa@com")
                .build();
        adminRepository.save(admin);

        // 질문 추가
        List<SurveyQuestion> surveyQuestions = new ArrayList<>();
        surveyQuestions.add(buildQuestion(1, "이름을 입력해주세요"));
        surveyQuestions.add(buildQuestion(2, "나이를 입력해주세요"));

        // 설문 양식 생성
        Survey newSurvey = Survey.builder()
                .admin(admin)
                .postDate(LocalDateTime.now())
                .deadLine(LocalDateTime.of(2022, 9, 30, 20, 0))
                .maximumAnswer(200)
                .surveyQuestionList(surveyQuestions)
                .build();

        surveyRepository.save(newSurvey);
        Optional<Survey> survey = surveyRepository.findById(newSurvey.getId());

        //when
        // 질문 수정
        List<SurveyQuestion> newSurveyQuestions = new ArrayList<>();
        newSurveyQuestions.add(buildQuestion(1, "이름을 입력해주세요"));
        newSurveyQuestions.add(buildQuestion(2, "주소를 입력해주세요"));

        survey.orElseThrow().updateQuestion(newSurveyQuestions);
        Optional<Survey> updatedSurvey = surveyRepository.findById(newSurvey.getId());

        //then;
        assertThat(updatedSurvey.orElseThrow().getMaximumAnswer()).isEqualTo(200);
        assertThat(updatedSurvey.orElseThrow().getSurveyQuestionList().get(1)).isEqualTo(newSurveyQuestions.get(1));
    }
    @Test
    @DisplayName(value = "설문이 삭제된다.")
    @Transactional
    public void deleteSurvey(){
        //given
        // 관리자 생성
        Admin admin = Admin.builder()
                .user(null)
                .name("KAI")
                .phone("2323")
                .company("Inflearn")
                .email("asdfa@com")
                .build();
        adminRepository.save(admin);

        // 질문 추가
        List<SurveyQuestion> surveyQuestions = new ArrayList<>();
        surveyQuestions.add(buildQuestion(1, "이름을 입력해주세요"));
        surveyQuestions.add(buildQuestion(2, "나이를 입력해주세요"));

        // 설문 양식 생성
        Survey newSurvey = Survey.builder()
                .admin(admin)
                .postDate(LocalDateTime.now())
                .deadLine(LocalDateTime.of(2022, 9, 30, 20, 0))
                .maximumAnswer(200)
                .surveyQuestionList(surveyQuestions)
                .build();

        surveyRepository.save(newSurvey);
        Optional<Survey> survey = surveyRepository.findById(newSurvey.getId());

        //when
        surveyRepository.delete(survey.orElseThrow());

        //then;
        assertThat(surveyRepository.findById(survey.get().getId())).isEmpty();
    }

    private SurveyQuestion buildQuestion(Integer count, String question) {
        return SurveyQuestion.builder()
                .number(count)
                .question(question)
                .build();
    }
}
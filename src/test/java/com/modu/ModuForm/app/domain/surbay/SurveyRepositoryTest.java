package com.modu.ModuForm.app.domain.surbay;

import com.modu.ModuForm.app.domain.user.Gender;
import com.modu.ModuForm.app.domain.user.Role;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
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

    @AfterEach
    public void cleanUp(){
        surveyRepository.deleteAll();
    }

    // 유저 생성
    @Transactional
    public User userInit() {
        User user = userRepository.save(User.builder()
                .name("기우")
                .nickName("Kai")
                .birth(19980101L)
                .gender(Gender.MAN)
                .email("asdf1234@naver.com")
                .phone(01032111L)
                .role(Role.USER)
                .company("AnyangUniv")
                .build());
        userRepository.save(user);
        return user;
    }

    @Transactional
    public List<SurveyQuestion> surveyQuestionInit() {
        // 질문 추가
        List<SurveyQuestion> surveyQuestions = new ArrayList<>();
        surveyQuestions.add(buildQuestion(0, "이름을 입력해주세요", null ,QuesType.SHORT));
        surveyQuestions.add(buildQuestion(1, "나이를 입력해주세요", "20|30|40|50" ,QuesType.MULTIPLE));
        surveyQuestions.add(buildQuestion(2, "주소를 입력해주세요", null ,QuesType.SHORT));
        return surveyQuestions;
    }

    @Transactional
    public Survey surveyInit(User user, List<SurveyQuestion> surveyQuestionList) {
        Survey newSurvey = Survey.builder()
                .user(user)
                .title("참여 조사")
                .postDate(LocalDateTime.now())
                .deadLine(LocalDateTime.of(2022, 9, 30, 20, 0))
                .maximumAnswer(200)
                .surveyQuestionList(surveyQuestionList)
                .build();

        surveyRepository.save(newSurvey);
        return newSurvey;
    }

    @Test
    @DisplayName(value = "설문이_등록된다.")
    @Transactional
    public void 설문이_등록된다() {
        //given
        User user = userInit();
        List<SurveyQuestion> surveyQuestionList = surveyQuestionInit();
        Survey newSurvey = surveyInit(user, surveyQuestionList);

        //when
        Optional<Survey> survey = surveyRepository.findById(newSurvey.getId());

        //then
        assertThat(survey.orElseThrow().getMaximumAnswer()).isEqualTo(200);
        assertThat(survey.orElseThrow().getDeadLine()).isEqualTo(LocalDateTime.of(2022,9,30,20,0));

        assertThat(survey.orElseThrow().getSurveyQuestionList().get(0)).isEqualTo(surveyQuestionList.get(0));
        assertThat(survey.orElseThrow().getSurveyQuestionList().get(1)).isEqualTo(surveyQuestionList.get(1));
        assertThat(survey.orElseThrow().getSurveyQuestionList().get(1).getDistractor()).isEqualTo(surveyQuestionList.get(1).getDistractor());
    }

    @Test
    @DisplayName(value = "설문이 수정된다.")
    @Transactional
    public void 설문이_수정된다(){
        //given
        User user = userInit();
        List<SurveyQuestion> surveyQuestionList = surveyQuestionInit();
        Survey newSurvey = surveyInit(user, surveyQuestionList);
        Optional<Survey> survey = surveyRepository.findById(newSurvey.getId());

        //when
        // 질문 수정
        List<SurveyQuestion> newSurveyQuestions = new ArrayList<>();
        newSurveyQuestions.add(buildQuestion(0, "hello", null,QuesType.SHORT));
        newSurveyQuestions.add(buildQuestion(1, "주소를 입력해주세요", null, QuesType.SHORT));

        survey.orElseThrow().updateQuestion(newSurveyQuestions);
        Optional<Survey> updatedSurvey = surveyRepository.findById(newSurvey.getId());

        //then
        assertThat(updatedSurvey.orElseThrow().getSurveyQuestionList().get(0).getQuestion()).isEqualTo("hello");
    }
    @Test
    @DisplayName(value = "설문이 삭제된다.")
    @Transactional
    public void 설문이_삭제된다(){
        //given
        User user = userInit();
        List<SurveyQuestion> surveyQuestionList = surveyQuestionInit();
        Survey newSurvey = surveyInit(user, surveyQuestionList);
        Optional<Survey> survey = surveyRepository.findById(newSurvey.getId());

        //when
        surveyRepository.delete(survey.orElseThrow());

        //then;
        assertThat(surveyRepository.findById(survey.get().getId())).isEmpty();
    }

    private SurveyQuestion buildQuestion(Integer count, String question, String distractor, QuesType quesType) {
        return SurveyQuestion.builder()
                .number(count)
                .questionType(quesType)
                .question(question)
                .distractor(distractor)
                .build();
    }
}
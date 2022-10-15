package com.modu.ModuForm.app.service.survey;

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

    @AfterEach
    void cleanUp() {
        surveyRepository.deleteAll();
    }
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

    private SurveyQuestion buildQuestion(Integer count, String question, String distractor, QuesType quesType) {
        return SurveyQuestion.builder()
                .number(count)
                .questionType(quesType)
                .question(question)
                .distractor(distractor)
                .build();
    }

    @DisplayName("설문 응답이 등록된다.")
    @Test
    @Transactional(noRollbackFor = IllegalAccessException.class)
    public void AnswerRegist(){
        //given
        User user = userInit();
        List<SurveyQuestion> surveyQuestionList = surveyQuestionInit();
        Survey survey = surveyInit(user, surveyQuestionList);

        //when
        List<String> answer = new ArrayList<>();
        answer.add("이기우");
        answer.add("25");
        answer.add("경기도");

        AnswerRequestDto answerRequestDto = AnswerRequestDto.builder()
                .userId(user.getId())
                .answerQuestion(answer)
                .build();

        Long answerId = answerService.save(answerRequestDto, survey.getId());

        //then
        Answer answerResult = answerRepository.findById(answerId).orElseThrow();
        assertThat(answerResult.getAnswerDataList().get(0).getAnswer()).isEqualTo("이기우");
        assertThat(answerResult.getAnswerDataList().get(1).getAnswer()).isEqualTo("25");
        assertThat(answerResult.getUser().getId()).isEqualTo(user.getId());
    }
}
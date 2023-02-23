package com.modu.ModuForm.app.domain.surbay.answer;

import com.modu.ModuForm.app.domain.surbay.QuesType;
import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.surbay.SurveyQuestion;
import com.modu.ModuForm.app.domain.surbay.SurveyRepository;
import com.modu.ModuForm.app.domain.user.common.Gender;
import com.modu.ModuForm.app.domain.user.common.Role;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
import com.modu.ModuForm.app.web.dto.answer.AnswerSave;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@TestPropertySource(value = "classpath:application-test-db.properties")
@DataJpaTest
class AnswerRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private AnswerRepository answerRepository;

    List<SurveyQuestion> testSurveyQuestion() {
        List<SurveyQuestion> surveyQuestions = new ArrayList<>();
        surveyQuestions.add(SurveyQuestion.builder()
                .number(0)
                .questionType(QuesType.SHORT)
                .question("이름을 입력해주세요")
                .distractor(null)
                .build());
        surveyQuestions.add(SurveyQuestion.builder()
                .number(1)
                .questionType(QuesType.MULTIPLE)
                .question("나이대를 입력해주세요")
                .distractor("20|30|40|50")
                .build());
        return surveyQuestions;
    }

    @DisplayName("응답이 등록된다.")
    @Test
    void save() {
        //given
        User ROSA = userRepository.save(User.builder()
                .name("예진")
                .nickName("Rosa")
                .birth(19940101L)
                .gender(Gender.WOMAN)
                .email("asdf1234@naver.com")
                .phone("01093129311")
                .role(Role.USER)
                .company("AnyangUniv")
                .build());

        User ROI = userRepository.save(User.builder()
                .name("기우")
                .nickName("Roi")
                .birth(19940101L)
                .gender(Gender.MAN)
                .email("a1234@naver.com")
                .phone("01091213311")
                .role(Role.USER)
                .company("AnyangUniv")
                .build());

        String title = "참여자 신상 조사 설문";
        Survey survey = surveyRepository.save(Survey.builder()
                .user(ROSA)
                .title(title)
                .deadLine(LocalDateTime.of(2022, 9, 30, 20, 0))
                .maximumAnswer(200)
                .surveyQuestionList(testSurveyQuestion())
                .build());

        //when
        List<AnswerData> answerDataList = new ArrayList<>();
        answerDataList.add(AnswerData.builder()
                .number(0)
                .answer("이기우")
                .build());
        answerDataList.add(AnswerData.builder()
                .number(1)
                .answer("20")
                .build());

        Answer answer = answerRepository.save(Answer.builder()
                .user(ROI)
                .survey(survey)
                .answerDataList(answerDataList)
                .anonymousFlag(true)
                .build());

        //then
        Answer findAnswer = answerRepository.findById(answer.getId()).orElseThrow();
        assertThat(findAnswer.getUser()).isEqualTo(ROI);
        assertThat(findAnswer.getAnonymousFlag()).isTrue();
        assertThat(findAnswer.getSurvey()).isEqualTo(survey);

        List<AnswerData> findAnswerDataList = findAnswer.getAnswerDataList();
        assertThat(findAnswerDataList.get(0).getNumber()).isEqualTo(0);
        assertThat(findAnswerDataList.get(1).getNumber()).isEqualTo(1);
        assertThat(findAnswerDataList.get(0).getAnswer()).isEqualTo("이기우");
        assertThat(findAnswerDataList.get(1).getAnswer()).isEqualTo("20");
    }

    @DisplayName("설문을 등록한 사람이 응답 객체를 생성하면 예외가 발생한다")
    @Test
    void saveFail() {
        //given
        User ROSA = userRepository.save(User.builder()
                .name("예진")
                .nickName("Rosa")
                .birth(19940101L)
                .gender(Gender.WOMAN)
                .email("asdf1234@naver.com")
                .phone("01093129311")
                .role(Role.USER)
                .company("AnyangUniv")
                .build());

        userRepository.save(User.builder()
                .name("기우")
                .nickName("Roi")
                .birth(19940101L)
                .gender(Gender.MAN)
                .email("a1234@naver.com")
                .phone("01091213311")
                .role(Role.USER)
                .company("AnyangUniv")
                .build());

        String title = "참여자 신상 조사 설문";
        Survey survey = surveyRepository.save(Survey.builder()
                .user(ROSA)
                .title(title)
                .deadLine(LocalDateTime.of(2022, 9, 30, 20, 0))
                .maximumAnswer(200)
                .surveyQuestionList(testSurveyQuestion())
                .build());

        List<AnswerData> answerDataList = new ArrayList<>();
        answerDataList.add(AnswerData.builder()
                .number(0)
                .answer("이기우")
                .build());
        answerDataList.add(AnswerData.builder()
                .number(1)
                .answer("20")
                .build());

        //then
        assertThatThrownBy(() -> Answer.builder()
                    .user(ROSA)
                    .survey(survey)
                    .anonymousFlag(true)
                    .answerDataList(answerDataList)
                    .build())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("익명 여부를 넣지 않으면 예외가 발생한다")
    @Test
    void saveFail2() {
        //given
        User ROSA = userRepository.save(User.builder()
                .name("예진")
                .nickName("Rosa")
                .birth(19940101L)
                .gender(Gender.WOMAN)
                .email("asdf1234@naver.com")
                .phone("01093129311")
                .role(Role.USER)
                .company("AnyangUniv")
                .build());

        User ROI = userRepository.save(User.builder()
                .name("기우")
                .nickName("Roi")
                .birth(19940101L)
                .gender(Gender.MAN)
                .email("a1234@naver.com")
                .phone("01091213311")
                .role(Role.USER)
                .company("AnyangUniv")
                .build());

        String title = "참여자 신상 조사 설문";
        Survey survey = surveyRepository.save(Survey.builder()
                .user(ROSA)
                .title(title)
                .deadLine(LocalDateTime.of(2022, 9, 30, 20, 0))
                .maximumAnswer(200)
                .surveyQuestionList(testSurveyQuestion())
                .build());

        List<AnswerData> answerDataList = new ArrayList<>();
        answerDataList.add(AnswerData.builder()
                .number(0)
                .answer("이기우")
                .build());
        answerDataList.add(AnswerData.builder()
                .number(1)
                .answer("20")
                .build());

        //when
        Answer answer = Answer.builder()
                .user(ROI)
                .survey(survey)
                .anonymousFlag(null)
                .answerDataList(answerDataList)
                .build();

        //then
        assertThatThrownBy(() -> answerRepository.save(answer))
                .isInstanceOf(RuntimeException.class);
    }

    @DisplayName("설문이 수정된다")
    @Test
    void edit() {
        //given
        User ROSA = userRepository.save(User.builder()
                .name("예진")
                .nickName("Rosa")
                .birth(19940101L)
                .gender(Gender.WOMAN)
                .email("asdf1234@naver.com")
                .phone("01093129311")
                .role(Role.USER)
                .company("AnyangUniv")
                .build());

        User ROI = userRepository.save(User.builder()
                .name("기우")
                .nickName("Roi")
                .birth(19940101L)
                .gender(Gender.MAN)
                .email("a1234@naver.com")
                .phone("01091213311")
                .role(Role.USER)
                .company("AnyangUniv")
                .build());

        String title = "참여자 신상 조사 설문";
        Survey survey = surveyRepository.save(Survey.builder()
                .user(ROSA)
                .title(title)
                .deadLine(LocalDateTime.of(2022, 9, 30, 20, 0))
                .maximumAnswer(200)
                .surveyQuestionList(testSurveyQuestion())
                .build());

        List<AnswerData> answerDataList = new ArrayList<>();
        answerDataList.add(AnswerData.builder()
                .number(0)
                .answer("이기우")
                .build());
        answerDataList.add(AnswerData.builder()
                .number(1)
                .answer("20")
                .build());

        Answer answer = answerRepository.save(Answer.builder()
                .user(ROI)
                .survey(survey)
                .anonymousFlag(true)
                .answerDataList(answerDataList)
                .build());

        //when
        List<String> editAnswerData = new ArrayList<>();
        editAnswerData.add("김강훈");
        editAnswerData.add("30");

        answer.update(AnswerSave.builder()
                .anonymousFlag(false)
                .answerList(editAnswerData)
                .build());
        answerRepository.flush();

        Answer updateAnswer = answerRepository.findById(answer.getId()).orElseThrow();

        //then
        assertThat(updateAnswer.getAnonymousFlag()).isFalse();
        assertThat(updateAnswer.getAnswerDataList().get(0).getAnswer()).isEqualTo("김강훈");
        assertThat(updateAnswer.getAnswerDataList().get(0).getNumber()).isEqualTo(0);
        assertThat(updateAnswer.getAnswerDataList().get(1).getAnswer()).isEqualTo("30");
        assertThat(updateAnswer.getAnswerDataList().get(1).getNumber()).isEqualTo(1);
    }

    @DisplayName("deleteAllBySurvey: 설문의 모든 응답을 삭제한다")
    @Test
    void findAllByUser() {
        //given
        User ROSA = userRepository.save(User.builder()
                .name("예진")
                .nickName("Rosa")
                .birth(19940101L)
                .gender(Gender.WOMAN)
                .email("asdf1234@naver.com")
                .phone("01093129311")
                .role(Role.USER)
                .company("AnyangUniv")
                .build());

        User ROI = userRepository.save(User.builder()
                .name("기우")
                .nickName("Roi")
                .birth(19940101L)
                .gender(Gender.MAN)
                .email("a1234@naver.com")
                .phone("01091213311")
                .role(Role.USER)
                .company("AnyangUniv")
                .build());

        User NOPE = userRepository.save(User.builder()
                .name("희준")
                .nickName("Nope")
                .birth(1911101L)
                .gender(Gender.MAN)
                .email("apppp4@naver.com")
                .phone("0111213311")
                .role(Role.USER)
                .company("AnyangUniv")
                .build());

        String title = "참여자 신상 조사 설문";
        Survey survey = surveyRepository.save(Survey.builder()
                .user(ROSA)
                .title(title)
                .deadLine(LocalDateTime.of(2022, 9, 30, 20, 0))
                .maximumAnswer(200)
                .surveyQuestionList(testSurveyQuestion())
                .build());

        List<AnswerData> answerDataList = new ArrayList<>();
        answerDataList.add(AnswerData.builder()
                .number(0)
                .answer("이기우")
                .build());
        answerDataList.add(AnswerData.builder()
                .number(1)
                .answer("20")
                .build());

        List<AnswerData> answerDataList2 = new ArrayList<>();
        answerDataList.add(AnswerData.builder()
                .number(0)
                .answer("양희준")
                .build());
        answerDataList.add(AnswerData.builder()
                .number(1)
                .answer("40")
                .build());

        Answer answer1 = answerRepository.save(Answer.builder()
                .user(ROI)
                .survey(survey)
                .anonymousFlag(true)
                .answerDataList(answerDataList)
                .build());
        Answer answer2 = answerRepository.save(Answer.builder()
                .user(NOPE)
                .survey(survey)
                .anonymousFlag(true)
                .answerDataList(answerDataList2)
                .build());

        //when
        answerRepository.deleteAllBySurvey(survey);
        answerRepository.flush();

        //then
        Answer find1 = answerRepository.findById(answer1.getId()).orElse(null);
        Answer find2 = answerRepository.findById(answer2.getId()).orElse(null);
        assertThat(find1).isNull();
        assertThat(find2).isNull();

        Survey findSurvey = surveyRepository.findById(survey.getId()).orElseThrow();
        assertThat(findSurvey.getAnswers()).isNull();
    }
}
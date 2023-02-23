package com.modu.ModuForm.app.domain.surbay;

import com.modu.ModuForm.app.domain.user.common.Gender;
import com.modu.ModuForm.app.domain.user.common.Role;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
public class SurveyRepositoryTest {
    @Autowired
    SurveyRepository surveyRepository;
    @Autowired
    UserRepository userRepository;

    @DisplayName(value = "설문이 등록된다.")
    @Test
    public void post() {
        //given
        User user = userRepository.save(User.builder()
                .name("예진")
                .nickName("Rosa")
                .birth(19940101L)
                .gender(Gender.WOMAN)
                .email("asdf1234@naver.com")
                .phone("01093129311")
                .role(Role.USER)
                .company("AnyangUniv")
                .build());

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
                .question("나이를 입력해주세요")
                .distractor("20|30|40|50")
                .build());

        String title = "참여자 신상 조사 설문";
        Survey newSurvey = Survey.builder()
                .user(user)
                .title(title)
                .deadLine(LocalDateTime.of(2022, 9, 30, 20, 0))
                .maximumAnswer(200)
                .surveyQuestionList(surveyQuestions)
                .build();

        //when
        Survey save = surveyRepository.saveAndFlush(newSurvey);
        Survey findSurvey = surveyRepository.findById(save.getId()).orElseThrow();

        //then
        assertThat(findSurvey.getMaximumAnswer()).isEqualTo(200);
        assertThat(findSurvey.getTitle()).isEqualTo(title);
        assertThat(findSurvey.getSurveyQuestionList().size()).isEqualTo(2);
        assertThat(findSurvey.getUser().getNickName()).isEqualTo("Rosa");
    }

    @DisplayName(value = "제목을 입력하지 않으면 설문이 등록에 실패한다.")
    @Test
    public void postFail() {
        //given
        User user = userRepository.save(User.builder()
                .name("예진")
                .nickName("Rosa")
                .birth(19940101L)
                .gender(Gender.WOMAN)
                .email("asdf1234@naver.com")
                .phone("01093129311")
                .role(Role.USER)
                .company("AnyangUniv")
                .build());

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
                .question("나이를 입력해주세요")
                .distractor("20|30|40|50")
                .build());

        Survey newSurvey = Survey.builder()
                .user(user)
                .title(null)
                .deadLine(LocalDateTime.of(2022, 9, 30, 20, 0))
                .maximumAnswer(200)
                .surveyQuestionList(surveyQuestions)
                .build();

        //then
        assertThatThrownBy(() -> surveyRepository.save(newSurvey)).isInstanceOf(RuntimeException.class);
    }

    @DisplayName(value = "User을 지정하지 않으면 설문이 등록에 실패한다.")
    @Test
    public void postFail2() {
        //given
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
                .question("나이를 입력해주세요")
                .distractor("20|30|40|50")
                .build());

        Survey newSurvey = Survey.builder()
                .user(null)
                .title(null)
                .deadLine(LocalDateTime.of(2022, 9, 30, 20, 0))
                .maximumAnswer(200)
                .surveyQuestionList(surveyQuestions)
                .build();

        //then
        assertThatThrownBy(() -> surveyRepository.save(newSurvey)).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName(value = "설문이 수정된다.")
    public void edit() {
        //given
        User user = userRepository.save(User.builder()
                .name("예진")
                .nickName("Rosa")
                .birth(19940101L)
                .gender(Gender.WOMAN)
                .email("asdf1234@naver.com")
                .phone("01093129311")
                .role(Role.USER)
                .company("AnyangUniv")
                .build());

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
                .question("나이를 입력해주세요")
                .distractor("20|30|40|50")
                .build());

        String title = "참여자 신상 조사 설문";
        Survey newSurvey = Survey.builder()
                .user(user)
                .title(title)
                .deadLine(LocalDateTime.of(2022, 9, 30, 20, 0))
                .maximumAnswer(200)
                .surveyQuestionList(surveyQuestions)
                .build();
        Survey survey = surveyRepository.saveAndFlush(newSurvey);

        //when
        List<SurveyQuestion> newSurveyQuestions = new ArrayList<>();
        newSurveyQuestions.add(
                SurveyQuestion.builder()
                        .number(0)
                        .questionType(QuesType.SHORT)
                        .question("hello")
                        .distractor(null)
                        .build());
        newSurveyQuestions.add(
                SurveyQuestion.builder()
                        .number(1)
                        .questionType(QuesType.MULTIPLE)
                        .question("주소를 입력해주세요")
                        .distractor("경기|강원|경남")
                        .build());

        survey.updateQuestion(newSurveyQuestions);
        Survey updatedSurvey = surveyRepository.findById(survey.getId()).orElseThrow();

        //then
        assertThat(updatedSurvey.getSurveyQuestionList().get(0).getQuestion()).isEqualTo("hello");
        assertThat(updatedSurvey.getSurveyQuestionList().get(0).getQuestionType()).isEqualTo(QuesType.SHORT);
        assertThat(updatedSurvey.getSurveyQuestionList().get(1).getQuestion()).isEqualTo("주소를 입력해주세요");
        assertThat(updatedSurvey.getSurveyQuestionList().get(1).getDistractor()).isEqualTo("경기|강원|경남");
        assertThat(updatedSurvey.getSurveyQuestionList().get(1).getQuestionType()).isEqualTo(QuesType.MULTIPLE);
    }

    @Test
    @DisplayName(value = "설문이 삭제된다.")
    public void delete() {
        //given
        User user = userRepository.save(User.builder()
                .name("예진")
                .nickName("Rosa")
                .birth(19940101L)
                .gender(Gender.WOMAN)
                .email("asdf1234@naver.com")
                .phone("01093129311")
                .role(Role.USER)
                .company("AnyangUniv")
                .build());

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
                .question("나이를 입력해주세요")
                .distractor("20|30|40|50")
                .build());

        String title = "참여자 신상 조사 설문";
        Survey newSurvey = Survey.builder()
                .user(user)
                .title(title)
                .deadLine(LocalDateTime.of(2022, 9, 30, 20, 0))
                .maximumAnswer(200)
                .surveyQuestionList(surveyQuestions)
                .build();
        Survey save = surveyRepository.saveAndFlush(newSurvey);

        //when
        surveyRepository.delete(save);

        //then
        assertThat(surveyRepository.findById(save.getId())).isEmpty();
    }

    @DisplayName("특정 유저가 작성한 설문을 모두 불러오는데 성공한다.")
    @Test
    void findSurveyByUser() {
        //given
        User user = userRepository.save(User.builder()
                .name("예진")
                .nickName("Rosa")
                .birth(19940101L)
                .gender(Gender.WOMAN)
                .email("asdf1234@naver.com")
                .phone("01093129311")
                .role(Role.USER)
                .company("AnyangUniv")
                .build());

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
                .question("나이를 입력해주세요")
                .distractor("20|30|40|50")
                .build());

        String title = "참여자 신상 조사 설문";
        Survey newSurvey = Survey.builder()
                .user(user)
                .title(title)
                .deadLine(LocalDateTime.of(2022, 9, 30, 20, 0))
                .maximumAnswer(200)
                .surveyQuestionList(surveyQuestions)
                .build();

        String title2 = "테스트 설문";
        Survey newSurvey2 = Survey.builder()
                .user(user)
                .title(title)
                .deadLine(LocalDateTime.of(2022, 9, 30, 20, 0))
                .maximumAnswer(200)
                .surveyQuestionList(surveyQuestions)
                .build();

        surveyRepository.save(newSurvey);
        surveyRepository.save(newSurvey2);

        //when
        List<Survey> surveysByUser = surveyRepository.findSurveysByUser(user);

        //then
        assertThat(surveysByUser.size()).isEqualTo(2);
        assertThat(surveysByUser.get(0).getTitle()).isEqualTo(title, title2);
        assertThat(surveysByUser.get(1).getTitle()).isEqualTo(title, title2);
        assertThat(surveysByUser.get(0).getUser()).isEqualTo(user);
        assertThat(surveysByUser.get(1).getUser()).isEqualTo(user);
    }

    @DisplayName("특정 유저가 작성한 설문이 없다면 빈 객체가 반환된다.")
    @Test
    void findSurveyByUserNoneSurvey() {
        //given
        User user = userRepository.save(User.builder()
                .name("예진")
                .nickName("Rosa")
                .birth(19940101L)
                .gender(Gender.WOMAN)
                .email("asdf1234@naver.com")
                .phone("01093129311")
                .role(Role.USER)
                .company("AnyangUniv")
                .build());

        //when
        List<Survey> surveysByUser = surveyRepository.findSurveysByUser(user);

        //then
        assertThat(surveysByUser).isEmpty();
    }

    @DisplayName("findAllByUser() 페이징 쿼리 : 첫번째 페이지를 불러온다")
    @Test
    void findAllByUser() {
        //given
        User user = userRepository.save(User.builder()
                .name("예진")
                .nickName("Rosa")
                .birth(19940101L)
                .gender(Gender.WOMAN)
                .email("asdf1234@naver.com")
                .phone("01093129311")
                .role(Role.USER)
                .company("AnyangUniv")
                .build());

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
                .question("나이를 입력해주세요")
                .distractor("20|30|40|50")
                .build());

        for (int i = 1; i < 31; i++) {
            String title = "참여자 신상 조사 설문" + i;
            Survey newSurvey = Survey.builder()
                    .user(user)
                    .title(title)
                    .deadLine(LocalDateTime.of(2022, 9, 30, 20, 0))
                    .maximumAnswer(200)
                    .surveyQuestionList(surveyQuestions)
                    .build();
            surveyRepository.save(newSurvey);
        }

        //when
        PageRequest pageRequest = PageRequest.of(0, 9);
        Page<Survey> surveyPage1 = surveyRepository.findAllByUser(user, pageRequest);

        //then
        List<Survey> content = surveyPage1.getContent();
        assertThat(surveyPage1.getTotalElements()).isEqualTo(30);
        assertThat(surveyPage1.getNumberOfElements()).isEqualTo(9);
        assertThat(surveyPage1.getTotalPages()).isEqualTo(4);
        assertThat(content.get(0).getTitle()).isEqualTo("참여자 신상 조사 설문1");
        assertThat(content.get(8).getTitle()).isEqualTo("참여자 신상 조사 설문9");
    }

    @DisplayName("findAllByUser() 페이징 쿼리 : 두번째 페이지를 불러온다")
    @Test
    void findAllByUser2() {
        //given
        User user = userRepository.save(User.builder()
                .name("예진")
                .nickName("Rosa")
                .birth(19940101L)
                .gender(Gender.WOMAN)
                .email("asdf1234@naver.com")
                .phone("01093129311")
                .role(Role.USER)
                .company("AnyangUniv")
                .build());

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
                .question("나이를 입력해주세요")
                .distractor("20|30|40|50")
                .build());

        for (int i = 1; i < 31; i++) {
            String title = "참여자 신상 조사 설문" + i;
            Survey newSurvey = Survey.builder()
                    .user(user)
                    .title(title)
                    .deadLine(LocalDateTime.of(2022, 9, 30, 20, 0))
                    .maximumAnswer(200)
                    .surveyQuestionList(surveyQuestions)
                    .build();
            surveyRepository.save(newSurvey);
        }

        //when
        PageRequest pageRequest = PageRequest.of(1, 9);
        Page<Survey> surveyPage1 = surveyRepository.findAllByUser(user, pageRequest);

        //then
        List<Survey> content = surveyPage1.getContent();
        assertThat(content.get(0).getTitle()).isEqualTo("참여자 신상 조사 설문10");
        assertThat(content.get(8).getTitle()).isEqualTo("참여자 신상 조사 설문18");
    }

    @DisplayName("findAll() 페이징 쿼리 : 첫 번째 페이지를 불러온다")
    @Test
    void findAllPageable() {
        //given
        User rosa = userRepository.save(User.builder()
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
                .email("asd34@naver.com")
                .phone("0109323411")
                .role(Role.USER)
                .company("AnyangUniv")
                .build());

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
                .question("나이를 입력해주세요")
                .distractor("20|30|40|50")
                .build());

        for (int i = 1; i < 6; i++) {
            String title = "참여자 신상 조사 설문" + i;
            Survey newSurvey = Survey.builder()
                    .user(rosa)
                    .title(title)
                    .deadLine(LocalDateTime.of(2022, 9, 30, 20, 0))
                    .maximumAnswer(200)
                    .surveyQuestionList(surveyQuestions)
                    .build();
            surveyRepository.save(newSurvey);
        }

        for (int i = 1; i < 6; i++) {
            String title = "테스트 설문" + i;
            Survey newSurvey = Survey.builder()
                    .user(rosa)
                    .title(title)
                    .deadLine(LocalDateTime.of(2022, 9, 30, 20, 0))
                    .maximumAnswer(200)
                    .surveyQuestionList(surveyQuestions)
                    .build();
            surveyRepository.save(newSurvey);
        }

        //when
        PageRequest pageRequest = PageRequest.of(0, 9);
        Page<Survey> surveyPage1 = surveyRepository.findAll(pageRequest);

        //then
        List<Survey> content = surveyPage1.getContent();
        assertThat(content.get(0).getTitle()).isEqualTo("참여자 신상 조사 설문1");
        assertThat(content.get(8).getTitle()).isEqualTo("테스트 설문4");
    }
}
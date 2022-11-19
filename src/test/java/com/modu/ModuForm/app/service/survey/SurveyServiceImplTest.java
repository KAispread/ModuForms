package com.modu.ModuForm.app.service.survey;

import com.modu.ModuForm.app.domain.surbay.QuesType;
import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.surbay.SurveyQuestion;
import com.modu.ModuForm.app.domain.surbay.SurveyRepository;
import com.modu.ModuForm.app.domain.user.Gender;
import com.modu.ModuForm.app.domain.user.Role;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
import com.modu.ModuForm.app.web.dto.survey.SurveyPage;
import com.modu.ModuForm.app.web.dto.survey.SurveySaveDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@Transactional
@TestPropertySource(value = "classpath:application-test-db.properties")
@SpringBootTest
public class SurveyServiceImplTest {
    @Autowired
    SurveyRepository surveyRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SurveyServiceImpl surveyService;

    @DisplayName("설문이 등록된다")
    @Test
    public void post() {
        //given
        User user = User.builder()
                .gender(Gender.MAN)
                .email("adfsd@naver.com")
                .phone("01032491031")
                .birth(19980112L)
                .role(Role.USER)
                .name("예진")
                .nickName("Rosa")
                .build();
        userRepository.save(user);

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

        Long saved = surveyService.save(saveDto, "Rosa");

        //when
        Survey survey = surveyRepository.findById(saved).orElse(null);
        User rosa = userRepository.findByNickName("Rosa").orElse(null);

        //then
        assert survey != null;
        assertThat(survey.getTitle()).isEqualTo("회식 참여 조사");
        assertThat(survey.getSurveyQuestionList().get(0).getQuestion()).isEqualTo("회식에 참여하십니까?");
        assert rosa != null;
        assertThat(rosa.getSurveyList()).contains(survey);
    }

    @Transactional
    @TestInstance(PER_CLASS)
    @DisplayName("페이징 API 테스트")
    @Nested
    public class FindAllPage {
        @BeforeAll
        void setUpData() {
            //given
            User user = User.builder()
                    .gender(Gender.MAN)
                    .email("adfsd@naver.com")
                    .phone("01032491031")
                    .birth(19980112L)
                    .role(Role.USER)
                    .name("예진")
                    .nickName("Rosa")
                    .build();
            userRepository.save(user);

            for (int i = 0; i < 91; i++) {
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
                        .title("회식 참여 조사" + i)
                        .description("회식 참여 조사를 위한 설문입니다." + i)
                        .deadLine("2022-10-06-15-30")
                        .maximumAnswer(2000 + i)
                        .surveyQuestionList(surveyQuestionList)
                        .build();

                surveyService.save(saveDto, "Rosa");
            }
        }

        @DisplayName("페이지의 정보가 반환된다.")
        @Test
        public void case1() {
            // when
            PageRequest maximumAnswer = PageRequest.of(0, 9, Sort.by("maximumAnswer").descending());
            SurveyPage allPages = surveyService.findAllPages(maximumAnswer, 1);

            //then
            assertThat(allPages.getCurrentPage()).isEqualTo(1);
            assertThat(allPages.getStartPage()).isEqualTo(1);
        }

        @DisplayName("페이지별로 데이터가 반환된다.")
        @Test
        public void case2() {
            // when
            PageRequest maximumAnswer1 = PageRequest.of(0, 9, Sort.by("maximumAnswer").descending());
            SurveyPage page1 = surveyService.findAllPages(maximumAnswer1, 1);

            PageRequest maximumAnswer2 = PageRequest.of(1, 9, Sort.by("maximumAnswer").descending());
            SurveyPage page2 = surveyService.findAllPages(maximumAnswer2, 1);

            //then
            assertThat(page1.getSurveyPreviews().get(0).getTitle()).isEqualTo("회식 참여 조사90");
            assertThat(page2.getSurveyPreviews().get(0).getTitle()).isEqualTo("회식 참여 조사81");
        }

        @DisplayName("sp(현재 페이지 파라미터)에 전체 페이지보다 큰 값을 넣으면 예외가 발생한다.")
        @Test
        public void case3() {
            // when
            PageRequest maximumAnswer = PageRequest.of(0, 9, Sort.by("maximumAnswer").descending());
            SurveyPage allPages = surveyService.findAllPages(maximumAnswer, 1);
            int illegalPage = allPages.getTotalPages() + 1;

            PageRequest pr = PageRequest.of(illegalPage, 9, Sort.by("maximumAnswer").descending());

            //then
            SurveyPage surveyPage = surveyService.findAllPages(pr, illegalPage);
            assertThat(surveyPage.getSurveyPreviews()).isEmpty();
        }
    }
}
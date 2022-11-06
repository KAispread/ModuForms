package com.modu.ModuForm.app.service.user;

import com.modu.ModuForm.app.DummyDataInit;
import com.modu.ModuForm.app.domain.user.Access;
import com.modu.ModuForm.app.domain.user.Gender;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
import com.modu.ModuForm.app.web.dto.user.LoginRequestDto;
import com.modu.ModuForm.app.web.dto.user.UserFormDetailsDto;
import com.modu.ModuForm.app.web.dto.user.UserRegisterDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@Transactional
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private DummyDataInit dummyData;

    private Long userPk;
    @BeforeAll
    void initUser() {
        User user = dummyData.userInit();
        userPk = user.getId();
        dummyData.accessInit();
    }

    @Transactional
    @DisplayName("회원가입 테스트")
    @Nested
    class registerTest {
        @DisplayName("회원가입에 성공한다")
        @Test
        void registerSuccess() {
            //given
            String id = "lol123";
            String pwd = "love123";
            String username = "희준";
            String nickname = "Kia";
            String email = "asdfas@gmail.com";

            UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                    .id(id)
                    .pwd(pwd)
                    .username(username)
                    .nickname(nickname)
                    .birth(19980101L)
                    .gender(Gender.MAN)
                    .email(email)
                    .phone("01012345611")
                    .company("KaKao")
                    .build();

            //when
            Long userId = userService.register(userRegisterDto);
            User user = userRepository.findById(userId).orElse(null);

            //then
            assertThat(user).isNotNull();
            assertEquals(user.getName(), username);
            assertEquals(user.getNickName(), nickname);
            assertEquals(user.getEmail(), email);
        }
    }

    @Transactional
    @DisplayName("로그인 테스트")
    @Nested
    class loginTest {
        @DisplayName("로그인에 성공한다")
        @Test
        void loginSuccess() {
            //given
            String user_id = dummyData.getUSER_ID();
            String password = dummyData.getPASSWORD();
            String userName = dummyData.getNAME();

            //when
            LoginRequestDto loginRequestDto = LoginRequestDto.builder()
                    .userId(user_id)
                    .password(password)
                    .build();
            Access userAccess = userService.login(loginRequestDto);

            //then
            assertThat(userAccess.getUserId()).isEqualTo(user_id);
            assertThat(userAccess.getPassword()).isEqualTo(password);
            assertThat(userAccess.getUser().getName()).isEqualTo(userName);
        }

        @DisplayName("아이디, 비밀번호를 잘못 입력할 경우 로그인에 실패한다")
        @Test
        void loginFail() {
            //then
            assertThrows(IllegalArgumentException.class, () -> userService.login(
                    LoginRequestDto.builder()
                            .userId("qioeurha1fbk")
                            .password("1232qsfjsbaa")
                            .build())
            );
        }
    }

    @Transactional
    @DisplayName("메인화면 사용자 설문, 응답 정보 테스트")
    @Nested
    class userDetailTest {
        @BeforeEach
        void surveyAnswerInit() {
            dummyData.surveyInit();
        }

        @DisplayName("사용자의 이름과 닉네임 정보가 로드된다")
        @Test
        void userDetailLoad() {
            //given
            Integer currentSurveyPage = 1;
            Integer currentAnswerPage = 1;
            PageRequest surveyPageRequest = PageRequest.of(0, 9, Sort.by("createdDate").descending());
            PageRequest answerPageRequest = PageRequest.of(0, 9, Sort.by("createdDate").descending());

            //when
            UserFormDetailsDto userFormDetails = userService.getUserFormDetails(surveyPageRequest, currentSurveyPage,
                                                                                answerPageRequest, currentAnswerPage, userPk);
            //then
            assertEquals(userFormDetails.getNickName(), dummyData.getNICKNAME());
            assertEquals(userFormDetails.getName(), dummyData.getNAME());
        }

        @DisplayName("사용자의 설문 등록 정보가 로드된다")
        @Test
        void userSurveyLoad() {
            //given
            Integer currentSurveyPage = 1;
            Integer currentAnswerPage = 1;
            PageRequest surveyPageRequest = PageRequest.of(0, 9, Sort.by("createdDate").descending());
            PageRequest answerPageRequest = PageRequest.of(0, 9, Sort.by("createdDate").descending());

            //when
            UserFormDetailsDto userFormDetails = userService.getUserFormDetails(surveyPageRequest, currentSurveyPage,
                    answerPageRequest, currentAnswerPage, userPk);

            //then
            assertEquals(userFormDetails.getSurveyPage().getCurrentPage(), currentSurveyPage);
            assertEquals(userFormDetails.getSurveyPage().getSurveyPreviews().get(0).getTitle(),
                    dummyData.getSURVEY_TITLE());
        }
    }
}
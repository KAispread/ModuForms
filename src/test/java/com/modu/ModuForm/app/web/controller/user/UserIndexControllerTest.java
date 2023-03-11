package com.modu.ModuForm.app.web.controller.user;

import com.modu.ModuForm.app.domain.user.acess.AccessRepository;
import com.modu.ModuForm.app.domain.user.UserRepository;
import com.modu.ModuForm.app.service.survey.SurveyService;
import com.modu.ModuForm.app.service.user.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.reactive.function.client.WebClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;


@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserIndexControllerTest {
    private MockMvc mvc;
    private WebClient webClient = WebClient.create("http://localhost:" + 8080);

    @Autowired
    private SurveyService surveyService;
    @Autowired
    private UserService userService;
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccessRepository accessRepository;

    @BeforeEach
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @DisplayName("로그인 페이지 로딩")
    @Test
    public void loadLoginPage() throws Exception {
        //when
        ResponseEntity<String> responseEntity = webClient.get().uri("/users/login").retrieve().toEntity(String.class).block();

        //then
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).contains("로그인 후 ModuForm의 모든 기능을 이용해보세요!");
    }

    @DisplayName("회원가입 페이지 로딩에 성공한다")
    @Test
    public void loadRegist() throws Exception {
        //when
        ResponseEntity<String> responseEntity = webClient.get().uri("/users/register").retrieve().toEntity(String.class).block();

        //then
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).contains("회원 정보 입력");
        assertThat(responseEntity.getBody()).contains("가입 하기");
    }

    @TestInstance(PER_CLASS)
    @DisplayName("로그인 동작 테스트")
    @Nested
    @Transactional
    class LoginUser {
        @BeforeEach
        void initUser() {

        }
        @Test
        void success() {

        }
    }
}
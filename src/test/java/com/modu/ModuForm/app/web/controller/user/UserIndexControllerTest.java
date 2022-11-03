package com.modu.ModuForm.app.web.controller.user;

import com.modu.ModuForm.app.DummyDataInit;
import com.modu.ModuForm.app.domain.surbay.SurveyRepository;
import com.modu.ModuForm.app.domain.user.Access;
import com.modu.ModuForm.app.domain.user.AccessRepository;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
import com.modu.ModuForm.app.web.dto.user.LoginRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class UserIndexControllerTest {
    private WebClient webClient = WebClient.create("http://localhost:" + 8080);

    @Autowired
    private DummyDataInit dummyDataInit;

    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private AccessRepository accessRepository;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void cleanUp() {
        surveyRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void 로그인페이지_로딩_성공() throws Exception {
        //when
        ResponseEntity<String> responseEntity = webClient.get().uri("/users/login").retrieve().toEntity(String.class).block();

        //then
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).contains("로그인이 필요합니다.");
    }

    @Test
    public void 회원가입페이지_로딩_성공() {
        //when
        ResponseEntity<String> responseEntity = webClient.get().uri("/users/register").retrieve().toEntity(String.class).block();

        //then
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).contains("회원 정보 입력");
        assertThat(responseEntity.getBody()).contains("가입 하기");
    }

    @Test
    public void 로그인_요청_성공() {
        //given
        User user = dummyDataInit.userInit(userRepository);
        Access access = dummyDataInit.accessInit(accessRepository ,user);
        LoginRequestDto loginRequestDto = LoginRequestDto.builder().userId(access.getUserId()).password(access.getPassword()).build();


        //when
        ResponseEntity<String> block = webClient.post().uri("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(loginRequestDto)
                .retrieve()
                .toEntity(String.class)
                .block();

        //then
        assertThat(block).isNotNull();
    }
}
package com.modu.ModuForm.app.web.controller.user;

import com.modu.ModuForm.app.domain.user.AccessRepository;
import com.modu.ModuForm.app.domain.user.Gender;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
import com.modu.ModuForm.app.web.dto.user.UserRegisterDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    private WebClient webClient = WebClient.create("http://localhost:" + 8080);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccessRepository accessRepository;

    @Autowired
    private UserController userController;

    @Test
    public void Register_성공한다() {
        //given
        String nickname = "Kasper";
        UserRegisterDto registerDto = UserRegisterDto.builder()
                .id("siadsf123")
                .pwd("asdfower123")
                .nickname(nickname)
                .username("기우")
                .birth(19980112L)
                .gender(Gender.MAN)
                .email("asdfa@comg.com")
                .phone("0123123123")
                .build();

        //when
        ResponseEntity<Long> responseEntity = webClient.post()
                .uri("/app/users")
                .bodyValue(registerDto)
                .retrieve()
                .toEntity(Long.class)
                .block();

        //then
        assert responseEntity != null;
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        User Kasper = userRepository.findByNickName(nickname).orElseThrow();
        assertThat(Kasper.getGender()).isEqualTo(Gender.MAN);
        assertThat(Kasper.getBirth()).isEqualTo(19980112L);
    }

    @Test
    public void 로그아웃_성공한다() {
        //
    }
}
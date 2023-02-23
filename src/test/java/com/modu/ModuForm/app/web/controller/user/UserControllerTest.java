//package com.modu.ModuForm.app.web.controller.user;
//
//import com.modu.ModuForm.app.domain.user.acess.AccessRepository;
//import com.modu.ModuForm.app.domain.user.common.Gender;
//import com.modu.ModuForm.app.domain.user.User;
//import com.modu.ModuForm.app.domain.user.UserRepository;
//import com.modu.ModuForm.app.web.dto.user.UserRegisterDto;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//public class UserControllerTest {
//    private final WebClient webClient = WebClient.create("http://localhost:" + 8080);
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private AccessRepository accessRepository;
//
//    @Autowired
//    private UserController userController;
//
//    @DisplayName("회원 가입에 성공한다")
////    @Test
//    public void register() {
//        //given
//        String nickname = "Kasper";
//        UserRegisterDto registerDto = UserRegisterDto.builder()
//                .id("siadsf123")
//                .pwd("asdfower123")
//                .nickname(nickname)
//                .username("기우")
//                .birth(19980112L)
//                .gender(Gender.MAN)
//                .email("asdfa@comg.com")
//                .phone("0123123123")
//                .build();
//
//        //when
//        ResponseEntity<Long> responseEntity = webClient.post()
//                .uri("/app/users")
//                .bodyValue(registerDto)
//                .retrieve()
//                .toEntity(Long.class)
//                .block();
//
//        //then
//        assert responseEntity != null;
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//
//        User Kasper = userRepository.findByNickName(nickname).orElseThrow();
//        assertThat(Kasper.getGender()).isEqualTo(Gender.MAN);
//        assertThat(Kasper.getBirth()).isEqualTo(19980112L);
//    }
//
//    @DisplayName("로그아웃에 성공한다.")
//    @Test
//    public void logout() {
//        //
//    }
//}
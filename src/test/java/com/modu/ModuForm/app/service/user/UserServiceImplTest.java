package com.modu.ModuForm.app.service.user;

import com.modu.ModuForm.app.domain.user.*;
import com.modu.ModuForm.app.web.dto.user.LoginRequestDto;
import com.modu.ModuForm.app.web.dto.user.UserRegisterDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(PER_CLASS)
@Transactional
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @MockBean(name = "userRepository")
    private UserRepository userRepository;
    @MockBean(name = "accessRepository")
    private AccessRepository accessRepository;

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
            User userEntity = userRegisterDto.toUserEntity();

            when(userRepository.save(any())).thenReturn(userEntity);
            when(accessRepository.save(any())).thenReturn(userRegisterDto.toAccessEntity(userEntity));

            //when
            Long userId = userService.register(userRegisterDto);

            //then
            assertThat(userId).isGreaterThan(0L);
        }

        @DisplayName("중복되는 ID를 입력할 경우 회원 가입에 실패한다.")
        @Test
        void registerException() {
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
            when(userRepository.saveAndFlush(any())).thenReturn(userRegisterDto.toUserEntity());
            when(accessRepository.save(any())).thenThrow(IllegalArgumentException.class);

            //then
            assertThrows(IllegalArgumentException.class, () ->
                    userService.register(UserRegisterDto.builder()
                            .id(id)
                            .pwd(pwd)
                            .username(username)
                            .nickname(nickname)
                            .birth(19980101L)
                            .gender(Gender.MAN)
                            .email(email)
                            .phone("01012345611")
                            .company("KaKao")
                            .build()));
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
            String user_id = "tile123";
            String password = "lost123";
            Access access = Access.builder()
                    .userId(user_id)
                    .password(password)
                    .build();
            when(accessRepository.findByUserId(any())).thenReturn(Optional.of(access));

            //when
            LoginRequestDto loginRequestDto = LoginRequestDto.builder()
                    .userId(user_id)
                    .password(password)
                    .build();
            Access userAccess = userService.login(loginRequestDto);

            //then
            assertThat(userAccess.getUserId()).isEqualTo(user_id);
            assertThat(userAccess.getPassword()).isEqualTo(password);
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
}
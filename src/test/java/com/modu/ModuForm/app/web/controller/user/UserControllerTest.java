package com.modu.ModuForm.app.web.controller.user;

import com.modu.ModuForm.app.domain.user.*;
import com.modu.ModuForm.app.web.dto.user.UserRegisterDto;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccessRepository accessRepository;

    @Autowired
    private UserController userController;

    @Transactional
    @DisplayName("회원 가입 성공")
    @Test
    public void 회원가입에_성공한다() {
        //given
        UserRegisterDto registerDto = UserRegisterDto.builder()
                .id("siadsf123")
                .pwd("asdfower123")
                .nickname("Kasper")
                .username("기우")
                .birth(19980112L)
                .gender("남자")
                .email("asdfa@comg.com")
                .phone(19980112L)
                .build();
        Long user = userController.register(registerDto);

        //when
        User user1 = userRepository.findById(user).orElseThrow();
        Access access = accessRepository.findByUser(user1).orElseThrow();

        //then
        assertThat(user1.getName()).isEqualTo("기우");
        assertThat(access.getUserId()).isEqualTo("siadsf123");
        assertThat(user1.getGender()).isEqualTo(Gender.MAN);
    }
}
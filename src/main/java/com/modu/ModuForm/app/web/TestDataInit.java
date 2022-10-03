package com.modu.ModuForm.app.web;

import com.modu.ModuForm.app.domain.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {
    private final UserRepository userRepository;
    private final AccessRepository accessRepository;

    @PostConstruct
    public void init() {
        User user = userRepository.save(User.builder()
                .name("기우")
                .birth(19980101L)
                .gender(Gender.MAN)
                .email("asdf1234@naver.com")
                .phone(0103213411L)
                .role(Role.USER)
                .build());
        accessRepository.save(Access.builder()
                .user(user)
                .userId("ppap012")
                .password("love112")
                .build());
    }
}

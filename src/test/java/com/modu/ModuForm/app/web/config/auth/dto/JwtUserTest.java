package com.modu.ModuForm.app.web.config.auth.dto;

import com.modu.ModuForm.app.domain.user.common.Gender;
import com.modu.ModuForm.app.domain.user.common.Role;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.web.config.auth.jwt.CustomJwtProvider;
import com.modu.ModuForm.app.web.config.auth.jwt.encrypt.AES256;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JwtUserTest {
    CustomJwtProvider customJwtProvider = new CustomJwtProvider(new AES256());

    @DisplayName("암호화된 JWT로 JwtUser 객체 생성에 성공한다.")
    @Test
    void generateJwtUserWithToken() {
        User user = User.builder()
                .role(Role.USER)
                .email("asdf@Nfae.com")
                .nickName("Hook")
                .name("공만")
                .phone("01093248529")
                .company("안양대학교")
                .birth(19980121L)
                .gender(Gender.MAN)
                .build();

        String encryptedToken = customJwtProvider.createEncryptedToken(user);
        final String encryptedJwt = customJwtProvider.ENCRYPT_PREFIX + encryptedToken;

        JwtUser jwtUser = customJwtProvider.getJwtUser(encryptedJwt);
        Assertions.assertThat(jwtUser.getNickname()).isEqualTo("Hook");
        Assertions.assertThat(jwtUser.getName()).isEqualTo("공만");
        Assertions.assertThat(jwtUser.getEmail()).isEqualTo("asdf@Nfae.com");
    }
}
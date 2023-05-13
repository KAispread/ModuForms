package com.modu.ModuForm.app.web.config.jwt;

import com.modu.ModuForm.app.domain.user.common.Gender;
import com.modu.ModuForm.app.domain.user.common.Role;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.web.config.jwt.encrypt.AES256;
import io.jsonwebtoken.Claims;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CustomJwtProviderTest {
    CustomJwtProvider customJwtProvider = new CustomJwtProvider(new AES256());

    @DisplayName("일반 JWT 테스트")
    @Nested
    class normalJwt {
        @DisplayName("JWT 토큰 생성에 성공한다")
        @Test
        void create() {
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
            customJwtProvider.createToken(user);
        }

        @DisplayName("JWT 토큰 생성 및 파싱에 성공한다 (토큰 생성 및 파싱에 걸리는 시간 측정[nano])")
        @Test
        void createAndParse() {
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
            long before = System.currentTimeMillis();

            String jwtToken = customJwtProvider.createToken(user);
            final String jwt = customJwtProvider.JWT_PREFIX + jwtToken;
            Claims claims = customJwtProvider.parseToken(jwt);

            long after = System.currentTimeMillis();
            System.out.printf("NORMAL JWT OVERHEAD [milli] : %d \n", after - before);

            Assertions.assertThat(claims.getIssuer()).isEqualTo("Modu");
            Assertions.assertThat(claims.get("name", String.class)).isEqualTo("공만");
            Assertions.assertThat(claims.get("nickname", String.class)).isEqualTo("Hook");
        }
    }

    @DisplayName("암호화 적용 JWT 테스트")
    @Nested
    class encryptedJwt {
        @DisplayName("암호화 JWT 토근 생성에 성공한다")
        @Test
        void encryptToken() {
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
            customJwtProvider.createEncryptedToken(user);
        }

        @DisplayName("암호화 JWT 토큰 생성, 파싱에 성공한다. (토큰 생성 및 파싱에 걸리는 시간 측정[nano])")
        @Test
        void cryptography() {
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
            long before = System.currentTimeMillis();
            String encryptedToken = customJwtProvider.createEncryptedToken(user);
            final String encryptedJwt = customJwtProvider.ENCRYPT_PREFIX + encryptedToken;
            Claims decryptedTokenClaims = customJwtProvider.parseEncryptedToken(encryptedJwt);

            long after = System.currentTimeMillis();
            System.out.printf("ENCRYPT JWT OVERHEAD [milli] : %d \n", after - before);

            Assertions.assertThat(decryptedTokenClaims.getIssuer()).isEqualTo("Modu");
            Assertions.assertThat(decryptedTokenClaims.get("name", String.class)).isEqualTo("공만");
            Assertions.assertThat(decryptedTokenClaims.get("nickname", String.class)).isEqualTo("Hook");
        }
    }
}
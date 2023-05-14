package com.modu.ModuForm.app.web.config.jwt;

import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.common.Gender;
import com.modu.ModuForm.app.domain.user.common.Role;
import com.modu.ModuForm.app.web.security.authentication.jwt.CustomJwtProvider;
import com.modu.ModuForm.app.web.security.authentication.jwt.JwtExtractor;
import com.modu.ModuForm.app.web.security.authentication.jwt.JwtInjector;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.modu.ModuForm.app.web.security.authentication.jwt.JwtType.ACCESS;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CustomJwtProviderTest {
    @Autowired
    private JwtInjector jwtInjector;
    @Autowired
    private JwtExtractor jwtExtractor;

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

            jwtInjector.createToken(user, ACCESS);
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

            String jwtToken = jwtInjector.createToken(user, ACCESS);
            final String jwt = CustomJwtProvider.JWT_PREFIX + jwtToken;
            Claims claims = jwtExtractor.parseToken(jwt);

            long after = System.currentTimeMillis();
            System.out.printf("NORMAL JWT OVERHEAD [milli] : %d \n", after - before);

            assertThat(claims.getIssuer()).isEqualTo("Modu");
            assertThat(claims.get("name", String.class)).isEqualTo("공만");
            assertThat(claims.get("nickname", String.class)).isEqualTo("Hook");
        }
    }
}
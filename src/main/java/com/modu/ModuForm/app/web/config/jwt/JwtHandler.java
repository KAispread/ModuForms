package com.modu.ModuForm.app.web.config.jwt;

import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.web.config.dto.JwtUser;
import com.modu.ModuForm.app.web.config.jwt.CustomJwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.modu.ModuForm.app.web.config.jwt.JwtCookie.ENCRYPT;
import static com.modu.ModuForm.app.web.config.jwt.JwtCookie.NORMAL;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtHandler {
    private final CustomJwtProvider jwtProvider;

    public Cookie createJwtCookie(User user) {
        String token = jwtProvider.JWT_PREFIX + jwtProvider.createToken(user);
        log.info("NORMAL TOKEN : {}", token);

        Cookie jwtCookie = new Cookie(NORMAL.getCookieName(), token);
        jwtCookie.setMaxAge(30 * 60);
        jwtCookie.setHttpOnly(false);
        jwtCookie.setPath("/");
        return jwtCookie;
    }

    public Cookie createEncryptJwtCookie(User user) {
        String token = jwtProvider.ENCRYPT_PREFIX + jwtProvider.createEncryptedToken(user);
        log.info("ENCRYPT TOKEN : {}", token);

        Cookie jwtCookie = new Cookie(ENCRYPT.getCookieName(), token);
        jwtCookie.setMaxAge(30 * 60);
        jwtCookie.setHttpOnly(false);
        jwtCookie.setPath("/");
        return jwtCookie;
    }

    public JwtUser getJwtUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        String token = getTokenFromCookie(cookies);
        return jwtProvider.getJwtUser(token);
    }

    private String getTokenFromCookie(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(ENCRYPT.getCookieName())) {
                return cookie.getValue();
            }
        }
        throw new IllegalArgumentException("JWT is Not valid");
    }

    public void invalidate(JwtCookie jwtCookie, HttpServletResponse response) {
        String name = jwtCookie.getCookieName();
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");

        response.addCookie(cookie);
    }
}

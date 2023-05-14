package com.modu.ModuForm.app.web.security.authentication.jwt;

import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.web.config.dto.JwtUser;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

import static com.modu.ModuForm.app.web.security.authentication.jwt.ClaimKey.ROLE;
import static com.modu.ModuForm.app.web.security.authentication.jwt.JwtType.ACCESS;
import static com.modu.ModuForm.app.web.security.authentication.jwt.JwtType.REFRESH;

@Slf4j
@RequiredArgsConstructor
public class CustomJwtProvider {
    public static final String JWT_PREFIX = "Bearer ";
    public static final String ISSUER = "Modu";

    private final JwtExtractor jwtExtractor;
    private final JwtInjector jwtInjector;

    public void createAccessRefreshToken(HttpServletResponse response, User user) {
        jwtInjector.injectToken(response, user, ACCESS);
        jwtInjector.injectToken(response, user, REFRESH);
    }

    public boolean validToken(HttpServletRequest request, HttpServletResponse response) {
        Claims access = jwtExtractor.parseToken(request, ACCESS);
        Claims refresh = jwtExtractor.parseToken(request, REFRESH);

        // Access Token 의 유효기간과 Refresh Token 의 유효기간을 확인
        // 두 토큰이 모두 유효하다면 Access Token 갱신
        if (validExpireTime(access)) {
            if (validExpireTime(refresh)) reInjectAccessToken(response, access);
            return true;
        }

        return false;
    }

    public void saveAuthentication(HttpServletRequest request) {
        Claims claims = jwtExtractor.parseToken(request, ACCESS);

        List<GrantedAuthority> roles = List.of(
                new SimpleGrantedAuthority(ROLE.getStringValue(claims))
        );
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(new JwtUser(claims), null, roles);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void reInjectAccessToken(HttpServletResponse response, final Claims claims) {
        jwtInjector.injectToken(response, claims, ACCESS);
    }

    private boolean validExpireTime(Claims claims) {
        return claims.getExpiration().after(new Date());
    }
}

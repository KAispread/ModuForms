package com.modu.ModuForm.app.web.security.authentication.jwt;

import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.web.security.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

import static com.modu.ModuForm.app.web.security.authentication.jwt.ClaimKey.*;
import static com.modu.ModuForm.app.web.security.authentication.jwt.CustomJwtProvider.ISSUER;

public class JwtInjector {
    private final JwtProperties jwtProperties;

    public JwtInjector(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public void injectToken(HttpServletResponse response, final User user, final JwtType jwtType) {
        final String token = createToken(user, jwtType);

        response.addHeader(jwtProperties.getHeader(jwtType), token);
    }

    public void injectToken(HttpServletResponse response, final Claims claims, final JwtType jwtType) {
        final String token = createToken(claims, jwtType);

        response.addHeader(jwtProperties.getHeader(jwtType), token);
    }

    public String createToken(User user, JwtType jwtType) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + Duration.ofMinutes(jwtProperties.getExpiration(jwtType)).toMillis());

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(ISSUER)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .claim(ID.getKey(), user.getId())
                .claim(NAME.getKey(), user.getName())
                .claim(NICKNAME.getKey(), getNickname(user))
                .claim(EMAIL.getKey(), user.getEmail())
                .claim(ROLE.getKey(), user.getRole())
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(jwtProperties.getSecretKey().getBytes()))
                .compact();
    }

    public String createToken(final Claims claims, final JwtType jwtType) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + Duration.ofMinutes(jwtProperties.getExpiration(jwtType)).toMillis());

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(ISSUER)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .claim(ID.getKey(), claims.get("id"))
                .claim(NAME.getKey(), claims.get("name"))
                .claim(NICKNAME.getKey(), claims.get("nickname"))
                .claim(EMAIL.getKey(), claims.get("email"))
                .claim(ROLE.getKey(), claims.get("role"))
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(jwtProperties.getSecretKey().getBytes()))
                .compact();
    }

    private String getNickname(User user) {
        String nickname = user.getNickName();
        if (nickname == null || nickname.isBlank()) {
            return user.getName();
        }
        return nickname;
    }
}

package com.modu.ModuForm.app.web.security.authentication.jwt;

import com.modu.ModuForm.app.exception.auth.AuthenticationExpireException;
import com.modu.ModuForm.app.web.config.dto.JwtUser;
import com.modu.ModuForm.app.web.security.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

import static com.modu.ModuForm.app.web.security.authentication.jwt.ClaimKey.*;
import static com.modu.ModuForm.app.web.security.authentication.jwt.CustomJwtProvider.JWT_PREFIX;

public class JwtExtractor {
    private final JwtParser jwtParser;

    @Autowired
    public JwtExtractor(JwtProperties jwtProperties) {
        this.jwtParser = Jwts.parserBuilder()
                .setSigningKey(Base64.getEncoder().encodeToString(jwtProperties.getSecretKey().getBytes()))
                .requireIssuer(CustomJwtProvider.ISSUER)
                .build();
    }

    public Claims parseToken(HttpServletRequest request, JwtType jwtType) {
        String token = request.getHeader(jwtType.getTokenFlag());

        return parseToken(token);
    }

    public Claims parseToken(String headerValue) throws SignatureException, ExpiredJwtException {
        validAuthorizationHeader(headerValue);
        String token = extractToken(headerValue);

        return jwtParser.parseClaimsJws(token).getBody();
    }

    public JwtUser getJwtUser(String token) {
        Claims claims = parseToken(token);

        return JwtUser.builder()
                .id(ID.getLongValue(claims))
                .name(NAME.getStringValue(claims))
                .nickname(NICKNAME.getStringValue(claims))
                .email(EMAIL.getStringValue(claims))
                .roleKey(ROLE.getStringValue(claims))
                .build();
    }

    private String extractToken(String authorizationHeader) {
        return authorizationHeader.substring(JWT_PREFIX.length());
    }

    private void validAuthorizationHeader(String header) {
        if (header == null || !header.startsWith(JWT_PREFIX)) {
            throw new IllegalArgumentException("잘못된 JWT 헤더입니다.");
        }
    }

    private void validExpireTime(Claims claims) {
        if (claims.getExpiration().before(new Date())) {
            throw new AuthenticationExpireException();
        }
    }
}

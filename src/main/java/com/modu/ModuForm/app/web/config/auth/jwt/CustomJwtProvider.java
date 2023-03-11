package com.modu.ModuForm.app.web.config.auth.jwt;

import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.web.config.auth.dto.JwtUser;
import com.modu.ModuForm.app.web.config.auth.jwt.encrypt.Cryptography;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class CustomJwtProvider {
    public static final String JWT_SECRET_KEY="236979CB6F1AD6B6A6184A31E6BE37DB3818CC36871E26235DD67DCFE4041492";

    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final Cryptography cryptography;
    public final String JWT_PREFIX = "Bearer|";
    public final String ENCRYPT_PREFIX = "Yank|";
    private final String ISSUER = "Modu";

    @Autowired
    public CustomJwtProvider(Cryptography cryptography) {
        this.cryptography = cryptography;
    }

    public String createToken(User user) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(ISSUER)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis()))
                .claim("id", user.getId())
                .claim("name", user.getName())
                .claim("nickname", getNickname(user))
                .claim("email", user.getEmail())
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(JWT_SECRET_KEY.getBytes()))
                .compact();
    }

    public String getNickname(User user) {
        String nickname = user.getNickName();
        if (nickname == null || nickname.isBlank()) {
            return user.getName();
        }
        return nickname;
    }

    public Claims parseToken(String authorizationHeader) throws SignatureException, ExpiredJwtException {
        validateAuthorizationHeader(authorizationHeader);
        String token = extractToken(authorizationHeader);

        JwtParser parser = getParser();
        return parser.parseClaimsJws(token).getBody();
    }

    private JwtParser getParser() {
        return Jwts.parserBuilder()
                .setSigningKey(Base64.getEncoder().encodeToString(JWT_SECRET_KEY.getBytes()))
                .requireIssuer(ISSUER)
                .build();
    }

    private void validateAuthorizationHeader(String header) {
        if (header == null || !header.startsWith(JWT_PREFIX)) {
            throw new IllegalArgumentException();
        }
    }

    private String extractToken(String authorizationHeader) {
        return authorizationHeader.substring(JWT_PREFIX.length());
    }

    public String createEncryptedToken(User user) {
        String token = createToken(user);
        String[] target = separateSignature(token);

        String encryptHeaderPayload;
        try {
            encryptHeaderPayload = cryptography.encrypt(target[0]);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return encryptHeaderPayload + "." + target[1];
    }

    private String[] separateSignature(String token) {
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException();
        }
        int lastIndexOfColon = token.lastIndexOf(".");

        return new String[]{token.substring(0, lastIndexOfColon), token.substring(lastIndexOfColon + 1)};
    }

    public Claims parseEncryptedToken(String authorizationHeader) throws SignatureException, ExpiredJwtException {
        validateEncryptedHeader(authorizationHeader);
        String encryptedToken = extractEncryptedToken(authorizationHeader);
        String[] parts = separateSignature(encryptedToken);

        String decryptedToken;
        try {
            decryptedToken = cryptography.decrypt(parts[0]) + "." + parts[1];
        } catch (Exception e) {
            throw new IllegalArgumentException("Fail to encrypt");
        }
        JwtParser parser = getParser();
        return parser.parseClaimsJws(decryptedToken).getBody();
    }

    private void validateEncryptedHeader(String header) {
        if (header == null || !header.startsWith(ENCRYPT_PREFIX)) {
            throw new IllegalArgumentException();
        }
    }

    private String extractEncryptedToken(String authorizationHeader) {
        return authorizationHeader.substring(ENCRYPT_PREFIX.length());
    }

    public JwtUser getJwtUser(String token) {
        Claims claims = parseEncryptedToken(token);
        return JwtUser.builder()
                .id(claims.get("id", Long.class))
                .name(claims.get("name", String.class))
                .nickname(claims.get("nickname", String.class))
                .email(claims.get("email", String.class))
                .build();
    }
}

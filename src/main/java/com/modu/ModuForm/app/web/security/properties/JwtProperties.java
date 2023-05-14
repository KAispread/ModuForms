package com.modu.ModuForm.app.web.security.properties;

import com.modu.ModuForm.app.web.security.authentication.jwt.JwtType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secretKey;
    private Map<String, TokenInfo> info;

    public int getExpiration(final JwtType type) {
        return info.get(type.getTokenFlag()).expiration;
    }

    public String getHeader(final JwtType type) {
        return info.get(type.getTokenFlag()).header;
    }

    @Getter
    static class TokenInfo {
        private final int expiration;
        private final String header;

        public TokenInfo(int expiration, String header) {
            this.expiration = expiration * 1000;
            this.header = header;
        }
    }
}

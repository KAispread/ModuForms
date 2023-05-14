package com.modu.ModuForm.app.web.config.dto;

import com.modu.ModuForm.app.web.security.authentication.jwt.ClaimKey;
import io.jsonwebtoken.Claims;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static com.modu.ModuForm.app.web.security.authentication.jwt.ClaimKey.*;

@NoArgsConstructor
@Getter
public class JwtUser implements Serializable {
    private Long id;
    private String name;
    private String nickname;
    private String email;
    private String roleKey;

    @Builder
    public JwtUser(Long id, String name, String nickname, String email, String roleKey) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.roleKey = roleKey;
    }

    public JwtUser(Claims claims) {
        this.id = ID.getLongValue(claims);
        this.name = NAME.getStringValue(claims);
        this.nickname = NICKNAME.getStringValue(claims);
        this.email = EMAIL.getStringValue(claims);
        this.roleKey = ROLE.getStringValue(claims);
    }
}

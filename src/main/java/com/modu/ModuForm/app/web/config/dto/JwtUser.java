package com.modu.ModuForm.app.web.config.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class JwtUser {
    private Long id;
    private String name;
    private String nickname;
    private String email;

    @Builder
    public JwtUser(Long id, String name, String nickname, String email) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
    }
}

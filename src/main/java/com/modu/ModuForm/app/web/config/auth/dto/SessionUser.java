package com.modu.ModuForm.app.web.config.auth.dto;

import com.modu.ModuForm.app.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private final Long id;
    private final String name;
    private final String nickname;
    private final String email;

    public SessionUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.nickname = getNickname(user);
        this.email = user.getEmail();
    }

    public String getNickname(User user) {
        String nickname = user.getNickName();
        if (nickname == null || nickname.isBlank()) {
            return user.getName();
        }
        return nickname;
    }
}

package com.modu.ModuForm.config.auth.dto;

import com.modu.ModuForm.app.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String nickname;
    private String email;

    public SessionUser(User user) {
        this.name = user.getName();
        this.nickname = user.getName();
        this.email = user.getEmail();
    }
}

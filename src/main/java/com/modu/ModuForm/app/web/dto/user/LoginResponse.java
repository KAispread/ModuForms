package com.modu.ModuForm.app.web.dto.user;

import com.modu.ModuForm.app.domain.user.common.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginResponse {
    private String name;
    private Role role;

    @Builder
    public LoginResponse(String name, Role role) {
        this.name = name;
        this.role = role;
    }
}

package com.modu.ModuForm.app.web.dto.user;

import com.modu.ModuForm.app.domain.user.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginResponseDto {
    private String name;
    private Role role;

    @Builder
    public LoginResponseDto(String name, Role role) {
        this.name = name;
        this.role = role;
    }
}

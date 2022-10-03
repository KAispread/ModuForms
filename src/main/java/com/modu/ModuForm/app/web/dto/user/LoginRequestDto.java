package com.modu.ModuForm.app.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginRequestDto {
    private String userId;
    private String password;

    @Builder
    public LoginRequestDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}

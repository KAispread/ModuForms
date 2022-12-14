package com.modu.ModuForm.app.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {
    @Length(min = 5, max = 16 , message = "아이디는 5자 이상 16자 미만으로 입력하세요.")
    private String userId;
    @Length(min = 5, max = 16 , message = "비밀 번호는 5자 이상 16자 미만으로 입력하세요.")
    private String password;

    @Builder
    public LoginRequest(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}

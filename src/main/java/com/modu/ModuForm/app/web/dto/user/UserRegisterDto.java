package com.modu.ModuForm.app.web.dto.user;

import com.modu.ModuForm.app.domain.user.Access;
import com.modu.ModuForm.app.domain.user.Gender;
import com.modu.ModuForm.app.domain.user.Role;
import com.modu.ModuForm.app.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserRegisterDto {
    private String id;
    private String pwd;
    private String username;
    private String nickname;
    private Long birth;
    private Gender gender;
    private String email;
    private String phone;
    private String company;

    @Builder
    public UserRegisterDto(String id, String pwd, String username, String nickname, Long birth, Gender gender, String email, String phone, String company) {
        this.id = id;
        this.pwd = pwd;
        this.username = username;
        this.nickname = nickname;
        this.birth = birth;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.company = company;
    }

    public User toUserEntity() {
        return User.builder()
                .name(username)
                .nickName(nickname)
                .birth(birth)
                .gender(gender)
                .email(email)
                .phone(phone)
                .role(Role.USER)
                .company(company)
                .build();
    }

    public Access toAccessEntity(User user) {
        return Access.builder()
                .user(user)
                .userId(id)
                .password(pwd)
                .build();
    }
}

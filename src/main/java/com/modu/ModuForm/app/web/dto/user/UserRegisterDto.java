package com.modu.ModuForm.app.web.dto.user;

import com.modu.ModuForm.app.domain.user.Access;
import com.modu.ModuForm.app.domain.user.Gender;
import com.modu.ModuForm.app.domain.user.Role;
import com.modu.ModuForm.app.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@Getter
public class UserRegisterDto {
    private String id;
    private String pwd;
    private String username;
    private Long birth;
    private String gender;
    private String email;
    private Long phone;

    private Gender userGender;

    @Builder
    public UserRegisterDto(String id, String pwd, String username, Long birth, String gender, String email, Long phone) {
        this.id = id;
        this.pwd = pwd;
        this.username = username;
        this.birth = birth;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
    }

    public User toUserEntity() {
        if (gender.equals(Gender.MAN.getTitle())) {
            userGender = Gender.MAN;
        } else if (gender.equals(Gender.WOMAN.getTitle())){
            userGender = Gender.WOMAN;
        } else {
            userGender = null;
        }
        return User.builder()
                .name(username)
                .birth(birth)
                .gender(userGender)
                .email(email)
                .phone(phone)
                .role(Role.USER)
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

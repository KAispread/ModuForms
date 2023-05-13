package com.modu.ModuForm.app.web.dto.user;

import com.modu.ModuForm.app.domain.user.acess.Access;
import com.modu.ModuForm.app.domain.user.common.Gender;
import com.modu.ModuForm.app.domain.user.common.Role;
import com.modu.ModuForm.app.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.*;

@NoArgsConstructor
@Setter
@Getter
public class UserRegister {
    @Length(min = 5, max = 16)
    private String id;
    @Length(min = 5, max = 16)
    private String pwd;
    @Length(min = 2, max = 20)
    @NotBlank
    private String username;
    @Length(min = 2, max = 20)
    @NotBlank
    private String nickname;
    @Range(min = 19000000, max = 20221128)
    @Positive
    private Long birth;
    @NotNull
    private Gender gender;
    @Email
    private String email;
    @Length(min = 11, max = 11)
    private String phone;
    private String company;

    @Builder
    public UserRegister(String id, String pwd, String username, String nickname, Long birth, Gender gender, String email, String phone, String company) {
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

    public Access toAccessEntity(User user, PasswordEncoder passwordEncoder) {
        return Access.builder()
                .users(user)
                .userId(id)
                .password(passwordEncoder.encode(pwd))
                .build();
    }
}

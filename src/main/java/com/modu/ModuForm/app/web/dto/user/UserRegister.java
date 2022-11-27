package com.modu.ModuForm.app.web.dto.user;

import com.modu.ModuForm.app.domain.user.Access;
import com.modu.ModuForm.app.domain.user.Gender;
import com.modu.ModuForm.app.domain.user.Role;
import com.modu.ModuForm.app.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@NoArgsConstructor
@Setter
@Getter
public class UserRegister {
    @Length(min = 5, max = 16 , message = "아이디는 5자 이상 16자 미만으로 입력하세요.")
    private String id;
    @Length(min = 5, max = 16 , message = "비밀 번호는 5자 이상 16자 미만으로 입력하세요.")
    private String pwd;
    @NotBlank
    private String username;
    @NotBlank
    private String nickname;
    @Positive
    private Long birth;
    @NotNull
    private Gender gender;
    @Email
    private String email;
    @Min(10)
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

    public Access toAccessEntity(User user) {
        return Access.builder()
                .user(user)
                .userId(id)
                .password(pwd)
                .build();
    }
}

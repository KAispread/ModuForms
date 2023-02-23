package com.modu.ModuForm.app.web.dto.user;

import com.modu.ModuForm.app.domain.user.common.Gender;
import com.modu.ModuForm.app.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDetails {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String nickname;
    private Long birth;
    @NotBlank
    private String gender;
    @Email
    private String email;
    private String phone;
    private String company;

    public UserDetails(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.nickname = user.getNickName();
        this.birth = user.getBirth();
        this.gender = Gender.convertTitle(user.getGender());
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.company = user.getCompany();
    }
}

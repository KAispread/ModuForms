package com.modu.ModuForm.app.domain.user;

import com.modu.ModuForm.app.domain.BaseTimeEntity;
import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.web.dto.user.UserDetails;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "USERS")
public class User extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String nickName;
    private Long birth;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column
    private String email;
    @Column(unique = true)
    private String phone;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private String company;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private final List<Survey> surveyList = new ArrayList<>();

    @Builder
    public User(String name, String nickName, Long birth, Gender gender, String email, String phone, Role role, String company) {
        this.name = name;
        this.nickName = nickName;
        this.birth = birth;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.company = company;
    }

    public void setSurveyList(Survey survey) {
        surveyList.add(survey);
    }
    public String getRoleKey() {
        return this.role.getKey();
    }
    public String getGenderTitle() {return this.gender.getTitle();}

    public void update(UserDetails userDetails) {
        this.name = userDetails.getName();
        this.birth = userDetails.getBirth();
        this.gender = Gender.convertTitleToGender(userDetails.getGender());
        this.email = userDetails.getEmail();
        this.phone = userDetails.getPhone();
        this.company = userDetails.getCompany();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId().equals(user.getId()) && getName().equals(user.getName()) && getNickName().equals(user.getNickName()) && getRole() == user.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getNickName(), getRole());
    }
}

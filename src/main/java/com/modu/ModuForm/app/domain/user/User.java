package com.modu.ModuForm.app.domain.user;

import com.modu.ModuForm.app.domain.surbay.Survey;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;
    @Column(nullable = false)
    private String name;
    private String nickName;
    private Long birth;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column
    private String email;
    @Column(nullable = false, unique = true)
    private Long phone;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private String company;

    @OneToMany(mappedBy = "user")
    private List<Survey> surveyList = new ArrayList<>();

    @Builder
    public User(String name, String nickName, Long birth, Gender gender, String email, Long phone, Role role, String company) {
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
}

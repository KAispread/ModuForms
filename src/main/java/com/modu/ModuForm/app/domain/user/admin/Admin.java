package com.modu.ModuForm.app.domain.user.admin;

import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Admin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADMIN_ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "admin")
    private List<Survey> surveyList = new ArrayList<>();

    public void setSurveyList(Survey survey) {
        this.surveyList.add(survey);
        survey.setAdmin(this);
    }

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    private String company;

    @Column(nullable = false)
    private String email;

    @Builder
    public Admin(User user, String name, String phone, String company, String email) {
        this.user = user;
        this.name = name;
        this.phone = phone;
        this.company = company;
        this.email = email;
    }
}

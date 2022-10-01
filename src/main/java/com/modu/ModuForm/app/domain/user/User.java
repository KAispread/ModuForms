package com.modu.ModuForm.app.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Builder
    public User(String name, Long birth, Gender gender, String email, Long phone, Role role) {
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
    public String getGenderTitle() {return this.gender.getTitle();}
}

package com.modu.ModuForm.app.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;
    @Column(nullable = false)
    private String name;
    private int age;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phone;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, int age, String email, String phone, Role role) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}

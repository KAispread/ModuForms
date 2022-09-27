package com.modu.ModuForm.app.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "LOGIN")
public class Login {
    @Column(name = "LOGIN_ID")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "ID", nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;
}

package com.modu.ModuForm.app.domain.user.acess;

import com.modu.ModuForm.app.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "ACCESS")
public class Access {
    @Column(name = "ACCESS_ID")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User users;

    @Column(name = "PERSONAL_ID", nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Builder
    public Access(User users, String userId, String password) {
        this.users = users;
        this.userId = userId;
        this.password = password;
    }
}

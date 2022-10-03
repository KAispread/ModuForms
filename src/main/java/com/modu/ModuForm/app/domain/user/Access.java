package com.modu.ModuForm.app.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "ACCESS")
public class Access {
    @Column(name = "ACCESS_ID")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "ID", nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Builder
    public Access(User user, String userId, String password) {
        this.user = user;
        this.userId = userId;
        this.password = password;
    }
}

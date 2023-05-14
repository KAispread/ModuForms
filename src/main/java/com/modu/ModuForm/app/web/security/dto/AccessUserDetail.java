package com.modu.ModuForm.app.web.security.dto;

import com.modu.ModuForm.app.domain.user.acess.Access;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class AccessUserDetail extends User {
    private final com.modu.ModuForm.app.domain.user.User user;

    public AccessUserDetail(Access access, Collection<? extends GrantedAuthority> authorities) {
        super(access.getUserId(), access.getPassword(), authorities);
        this.user = access.getUsers();
    }
}

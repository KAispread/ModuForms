package com.modu.ModuForm.app.web.security.authentication;

import com.modu.ModuForm.app.domain.user.acess.Access;
import com.modu.ModuForm.app.domain.user.acess.AccessRepository;
import com.modu.ModuForm.app.exception.nosuch.NoSuchUserIdException;
import com.modu.ModuForm.app.web.security.dto.AccessUserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FormUserDetailService implements UserDetailsService {
    private final AccessRepository accessRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Access 정보
        Access access = accessRepository.findByUserId(username)
                .orElseThrow(NoSuchUserIdException::new);

        // 권한
        List<GrantedAuthority> roles = List.of(new SimpleGrantedAuthority(access.getUsers().getRoleKey()));

        return new AccessUserDetail(access, roles);
    }
}

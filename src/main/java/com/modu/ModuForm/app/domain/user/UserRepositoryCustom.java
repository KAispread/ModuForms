package com.modu.ModuForm.app.domain.user;

import com.modu.ModuForm.app.web.dto.user.UserDetails;

import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<UserDetails> getDetail(Long id);
}

package com.modu.ModuForm.app.service.user;

import com.modu.ModuForm.app.domain.user.acess.Access;
import com.modu.ModuForm.app.web.dto.user.LoginRequest;
import com.modu.ModuForm.app.web.dto.user.UserDetails;
import com.modu.ModuForm.app.web.dto.user.UserRegister;

public interface UserService {
    Access login(LoginRequest loginRequest);
    Long register(UserRegister userRequestDto);
    UserDetails getUserDetails(Long id);
    Long getUserPk(String nickName);
    Long update(UserDetails userDetails, Long userId);
}

package com.modu.ModuForm.app.service.user;

import com.modu.ModuForm.app.domain.user.Access;
import com.modu.ModuForm.app.web.dto.user.LoginRequestDto;
import com.modu.ModuForm.app.web.dto.user.UserDetailsDto;
import com.modu.ModuForm.app.web.dto.user.UserRegisterDto;

public interface UserService {
    Access login(LoginRequestDto loginRequestDto);
    Long register(UserRegisterDto userRequestDto);
    UserDetailsDto getUserDetails(Long id);
    Long getUserPk(String nickName);
}

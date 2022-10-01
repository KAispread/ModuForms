package com.modu.ModuForm.app.service.user;

import com.modu.ModuForm.app.web.dto.user.AdminRequestDto;
import com.modu.ModuForm.app.web.dto.user.LoginRequestDto;
import com.modu.ModuForm.app.web.dto.user.UserRegisterDto;
import com.modu.ModuForm.app.web.dto.user.UserResponseDto;

public interface UserService {
    public UserResponseDto login(LoginRequestDto loginRequestDto);
    public Long register(UserRegisterDto userRequestDto);
    public Long updateUser(Long id, UserRegisterDto userRequestDto);
    public Long createAdmin(Long id, AdminRequestDto adminRequestDto);
    public UserResponseDto viewProfile(Long id);
}

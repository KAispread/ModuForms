package com.modu.ModuForm.app.service.user;

import com.modu.ModuForm.app.web.dto.user.AdminRequestDto;
import com.modu.ModuForm.app.web.dto.user.LoginRequestDto;
import com.modu.ModuForm.app.web.dto.user.UserRequestDto;
import com.modu.ModuForm.app.web.dto.user.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    @Override
    public UserResponseDto login(LoginRequestDto loginRequestDto) {
        return null;
    }

    @Override
    public Long signUp(UserRequestDto userRequestDto) {
        return null;
    }

    @Override
    public Long updateUser(Long id, UserRequestDto userRequestDto) {
        return null;
    }

    @Override
    public Long createAdmin(Long id, AdminRequestDto adminRequestDto) {
        return null;
    }

    @Override
    public UserResponseDto viewProfile(Long id) {
        return null;
    }
}

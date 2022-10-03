package com.modu.ModuForm.app.service.user;

import com.modu.ModuForm.app.domain.user.Access;
import com.modu.ModuForm.app.domain.user.AccessRepository;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
import com.modu.ModuForm.app.web.dto.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final AccessRepository accessRepository;

    @Override
    public Access login(LoginRequestDto loginRequestDto) {
        return accessRepository.findByUserId(loginRequestDto.getUserId())
                .filter(access -> access.getPassword().equals(loginRequestDto.getPassword()))
                .orElse(null);
    }

    @Override
    public Long register(UserRegisterDto registerDto) {
        User user = userRepository.saveAndFlush(registerDto.toUserEntity());
        accessRepository.save(registerDto.toAccessEntity(user));

        return user.getId();
    }

    @Override
    public Long updateUser(Long id, UserRegisterDto userRequestDto) {
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

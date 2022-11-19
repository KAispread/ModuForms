package com.modu.ModuForm.app.service.user;

import com.modu.ModuForm.app.domain.user.Access;
import com.modu.ModuForm.app.domain.user.AccessRepository;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
import com.modu.ModuForm.app.service.PerfLog;
import com.modu.ModuForm.app.web.dto.user.LoginRequestDto;
import com.modu.ModuForm.app.web.dto.user.UserDetailsDto;
import com.modu.ModuForm.app.web.dto.user.UserRegisterDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final AccessRepository accessRepository;

    @PerfLog
    @Override
    @Transactional(readOnly = true)
    public Access login(LoginRequestDto loginRequestDto) {
        return accessRepository.findByUserId(loginRequestDto.getUserId())
                .filter(access -> access.getPassword().equals(loginRequestDto.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("잘못된 ID, PW 입니다."));
    }

    @PerfLog
    @Override
    @Transactional
    public Long register(UserRegisterDto registerDto) {
        User user = userRepository.save(registerDto.toUserEntity());
        accessRepository.save(registerDto.toAccessEntity(user));
        return user.getId();
    }

    @PerfLog
    @Override
    @Transactional(readOnly = true)
    public UserDetailsDto getUserDetails(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 정보가 존재하지 않습니다."));
        return new UserDetailsDto(user);
    }

    @PerfLog
    @Override
    @Transactional(readOnly = true)
    public Long getUserPk(String nickName) {
        return userRepository.findByNickName(nickName)
                .orElseThrow(() -> new IllegalArgumentException("유저 정보에 해당 닉네임이 존재하지 않습니다."))
                .getId();
    }
}

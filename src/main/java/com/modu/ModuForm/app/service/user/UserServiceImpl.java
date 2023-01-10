package com.modu.ModuForm.app.service.user;

import com.modu.ModuForm.app.domain.user.Access;
import com.modu.ModuForm.app.domain.user.AccessRepository;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
import com.modu.ModuForm.app.exception.invalid.InvalidUserIdPwException;
import com.modu.ModuForm.app.exception.invalid.InvalidUserUsernameException;
import com.modu.ModuForm.app.exception.nosuch.NoSuchUserIdException;
import com.modu.ModuForm.app.service.PerfLog;
import com.modu.ModuForm.app.web.dto.user.LoginRequest;
import com.modu.ModuForm.app.web.dto.user.UserDetails;
import com.modu.ModuForm.app.web.dto.user.UserRegister;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final AccessRepository accessRepository;

    @PerfLog
    @Override
    @Transactional(readOnly = true)
    public Access login(LoginRequest loginRequest) {
        return accessRepository.findByUserId(loginRequest.getUserId())
                .filter(access -> access.getPassword().equals(loginRequest.getPassword()))
                .orElseThrow(InvalidUserIdPwException::new);
    }

    @PerfLog
    @Override
    public Long register(UserRegister registerDto) {
        User user = userRepository.save(registerDto.toUserEntity());
        accessRepository.save(registerDto.toAccessEntity(user));
        return user.getId();
    }

    @PerfLog
    @Override
    @Transactional(readOnly = true)
    public UserDetails getUserDetails(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(NoSuchUserIdException::new);
        return new UserDetails(user);
    }

    @PerfLog
    @Override
    @Transactional(readOnly = true)
    public Long getUserPk(String nickName) {
        return userRepository.findByNickName(nickName)
                .orElseThrow(InvalidUserUsernameException::new)
                .getId();
    }

    @Override
    public Long update(UserDetails userDetails, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(NoSuchUserIdException::new);
        user.update(userDetails);
        return user.getId();
    }
}

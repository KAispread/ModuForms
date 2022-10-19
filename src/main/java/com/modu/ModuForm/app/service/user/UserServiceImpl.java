package com.modu.ModuForm.app.service.user;

import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.surbay.SurveyRepository;
import com.modu.ModuForm.app.domain.surbay.answer.Answer;
import com.modu.ModuForm.app.domain.surbay.answer.AnswerRepository;
import com.modu.ModuForm.app.domain.user.Access;
import com.modu.ModuForm.app.domain.user.AccessRepository;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
import com.modu.ModuForm.app.service.PerfLog;
import com.modu.ModuForm.app.web.dto.user.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final AccessRepository accessRepository;
    private final AnswerRepository answerRepository;
    private final SurveyRepository surveyRepository;

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
        User user = userRepository.saveAndFlush(registerDto.toUserEntity());
        accessRepository.save(registerDto.toAccessEntity(user));
        return user.getId();
    }

    @PerfLog
    @Override
    @Transactional(readOnly = true)
    public UserFormDetailsDto getUserFormDetails(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        List<Answer> answerList = answerRepository.findAnswersByUser(user);
        List<Survey> surveyList = surveyRepository.findSurveysByUser(user);
        return new UserFormDetailsDto(user, answerList, surveyList);
    }

    @Override
    public Long updateUser(Long id, UserRegisterDto userRequestDto) {
        return null;
    }

    @Override
    public UserDetailsDto getUserDetails(Long id) {
        return null;
    }

    @PerfLog
    @Override
    @Transactional(readOnly = true)
    public Long getUserPk(String nickName) {
        return userRepository.findByNickName(nickName).orElseThrow().getId();
    }
}

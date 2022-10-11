package com.modu.ModuForm.app.service.user;

import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.surbay.SurveyRepository;
import com.modu.ModuForm.app.domain.surbay.answer.Answer;
import com.modu.ModuForm.app.domain.surbay.answer.AnswerRepository;
import com.modu.ModuForm.app.domain.user.Access;
import com.modu.ModuForm.app.domain.user.AccessRepository;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
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

    @Override
    public Access login(LoginRequestDto loginRequestDto) {
        return accessRepository.findByUserId(loginRequestDto.getUserId())
                .filter(access -> access.getPassword().equals(loginRequestDto.getPassword()))
                .orElse(null);
    }

    @Transactional
    @Override
    public Long register(UserRegisterDto registerDto) {
        User user = userRepository.saveAndFlush(registerDto.toUserEntity());
        accessRepository.save(registerDto.toAccessEntity(user));

        log.info("{}: has registered", user.getNickName());
        return user.getId();
    }

    @Override
    public UserSubDetailsDto getUserSubDetails(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        List<Answer> answerList = answerRepository.findAnswersByUser(user);
        List<Survey> surveyList = surveyRepository.findSurveysByUser(user);
        return new UserSubDetailsDto(user, answerList, surveyList);
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
    public UserDetailsDto getUserDetails(Long id) {
        return null;
    }

    @Override
    public Long getUserPk(String nickName) {
        return userRepository.findByNickName(nickName).orElseThrow().getId();
    }
}

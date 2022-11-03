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
import com.modu.ModuForm.app.web.dto.user.LoginRequestDto;
import com.modu.ModuForm.app.web.dto.user.UserDetailsDto;
import com.modu.ModuForm.app.web.dto.user.UserFormDetailsDto;
import com.modu.ModuForm.app.web.dto.user.UserRegisterDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public UserFormDetailsDto getUserFormDetails(Pageable surveyPage, Integer currentSurveyPage, Pageable answerPage, Integer currentAnswerPage, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        Page<Survey> surveyPageList = surveyRepository.findAllByUser(user, surveyPage);
        Page<Answer> answerPageList = answerRepository.findAllByUser(user, answerPage);

        return new UserFormDetailsDto(user, surveyPageList, currentSurveyPage, answerPageList, currentAnswerPage);
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

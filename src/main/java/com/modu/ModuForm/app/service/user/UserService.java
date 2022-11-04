package com.modu.ModuForm.app.service.user;

import com.modu.ModuForm.app.domain.user.Access;
import com.modu.ModuForm.app.web.dto.user.*;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Access login(LoginRequestDto loginRequestDto);
    Long register(UserRegisterDto userRequestDto);
    UserFormDetailsDto getUserFormDetails(Pageable surveyPage, Integer currentSurveyPage, Pageable answerPage, Integer currentAnswerPage, Long id);
    Long update(Long id, UserRegisterDto userRequestDto);
    UserDetailsDto getUserDetails(Long id);
    Long getUserPk(String nickName);
}

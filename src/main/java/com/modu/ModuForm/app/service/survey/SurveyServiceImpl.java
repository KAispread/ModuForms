package com.modu.ModuForm.app.service.survey;

import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.surbay.SurveyRepository;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
import com.modu.ModuForm.app.web.dto.survey.SurveyCheckDto;
import com.modu.ModuForm.app.web.dto.survey.SurveySaveDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class SurveyServiceImpl implements SurveyService{
    private final SurveyRepository surveyRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Long save(SurveySaveDto surveySaveDto, String nickName) {
        User user = userRepository.findByNickName(nickName).orElseThrow(() -> new IllegalArgumentException("일치하는 사용자가 없습니다."));
        Survey survey = surveyRepository.save(surveySaveDto.toSurveyEntity(user));

        log.info("{}: saved survey = {}", user.getNickName(), survey.getTitle());
        return survey.getId();
    }

    @Override
    public Long update() {
        return null;
    }

    @Override
    public SurveyCheckDto getSurveyCheckDto(Long id) {
        Survey survey = surveyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        return new SurveyCheckDto(survey);
    }
}

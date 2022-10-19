package com.modu.ModuForm.app.service.survey;

import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.surbay.SurveyRepository;
import com.modu.ModuForm.app.domain.surbay.answer.AnswerRepository;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
import com.modu.ModuForm.app.service.PerfLog;
import com.modu.ModuForm.app.web.dto.SurveyPreview;
import com.modu.ModuForm.app.web.dto.survey.SurveyCheckDto;
import com.modu.ModuForm.app.web.dto.survey.SurveySaveDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class SurveyServiceImpl implements SurveyService{
    private final SurveyRepository surveyRepository;
    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;

    @PerfLog
    @Override
    @Transactional
    public Long save(SurveySaveDto surveySaveDto, String nickName) {
        User user = userRepository.findByNickName(nickName).orElseThrow(() -> new IllegalArgumentException("일치하는 사용자가 없습니다."));
        Survey survey = surveyRepository.save(surveySaveDto.toSurveyEntity(user));

        return survey.getId();
    }

    @PerfLog
    @Override
    @Transactional
    public Long update(Long id, SurveySaveDto surveySaveDto) {
        Survey survey = surveyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        answerRepository.deleteAllBySurvey(survey);
        survey.update(surveySaveDto);

        return survey.getId();
    }

    @PerfLog
    @Override
    @Transactional(readOnly = true)
    public List<SurveyPreview> findAllOrderBy() {
        return surveyRepository.findAllByOrderByCreatedDateDesc().stream()
                .map(SurveyPreview::new)
                .collect(Collectors.toList());
    }

    @PerfLog
    @Override
    @Transactional(readOnly = true)
    public SurveyCheckDto getSurveyCheckDto(Long id) {
        Survey survey = surveyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        return new SurveyCheckDto(survey);
    }

    @PerfLog
    @Transactional
    @Override
    public Long delete(Long id) {
        Survey survey = surveyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        surveyRepository.delete(survey);
        return id;
    }
}

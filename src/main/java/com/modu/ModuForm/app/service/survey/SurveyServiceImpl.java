package com.modu.ModuForm.app.service.survey;

import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.surbay.SurveyRepository;
import com.modu.ModuForm.app.domain.surbay.answer.Answer;
import com.modu.ModuForm.app.domain.surbay.answer.AnswerRepository;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
import com.modu.ModuForm.app.exception.nosuch.NoSuchSurveyIdException;
import com.modu.ModuForm.app.service.PerfLog;
import com.modu.ModuForm.app.web.dto.survey.SurveyAnswerCheck;
import com.modu.ModuForm.app.web.dto.survey.SurveyCheck;
import com.modu.ModuForm.app.web.dto.survey.SurveyPage;
import com.modu.ModuForm.app.web.dto.survey.SurveySave;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Long save(SurveySave surveySave, String nickName) {
        User user = userRepository.findByNickName(nickName).orElseThrow(() -> new IllegalArgumentException("일치하는 사용자가 없습니다."));
        Survey survey = surveyRepository.save(surveySave.toSurveyEntity(user));

        return survey.getId();
    }

    @PerfLog
    @Override
    @Transactional
    public Long update(Long id, SurveySave surveySave) {
        Survey survey = surveyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 설문이 없습니다."));
        answerRepository.deleteAllBySurvey(survey);
        survey.update(surveySave);

        return survey.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public SurveyPage findAllPages(Pageable pageable){
        Page<Survey> surveyPage = surveyRepository.findAll(pageable);
        return new SurveyPage(surveyPage);
    }

    @PerfLog
    @Override
    @Transactional(readOnly = true)
    public SurveyCheck getSurveyCheckDto(Long id) {
        Survey survey = surveyRepository.findById(id).orElseThrow(NoSuchSurveyIdException::new);
        return new SurveyCheck(survey);
    }

    @PerfLog
    @Transactional
    @Override
    public Long delete(Long id) {
        Survey survey = surveyRepository.findById(id).orElseThrow(NoSuchSurveyIdException::new);
        surveyRepository.delete(survey);
        return id;
    }

    @Transactional(readOnly = true)
    @Override
    public SurveyAnswerCheck getSurveyAnswerCheckDto(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId).orElseThrow(NoSuchSurveyIdException::new);
        List<Answer> allAnswer = answerRepository.findAllBySurvey(survey);

        return new SurveyAnswerCheck(survey, allAnswer);
    }

}

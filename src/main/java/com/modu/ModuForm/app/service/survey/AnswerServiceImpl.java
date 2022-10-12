package com.modu.ModuForm.app.service.survey;

import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.surbay.SurveyRepository;
import com.modu.ModuForm.app.domain.surbay.answer.AnswerRepository;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
import com.modu.ModuForm.app.web.dto.survey.AnswerRequestDto;
import com.modu.ModuForm.app.web.dto.survey.SurveyListResponseDto;
import com.modu.ModuForm.app.web.dto.user.AnswerResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AnswerServiceImpl implements AnswerService{
    private final SurveyRepository surveyRepository;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Long save(AnswerRequestDto answerRequestDto, Long surveyId) {
        User user = userRepository.getReferenceById(answerRequestDto.getUserId());
        Survey survey = surveyRepository.getReferenceById(surveyId);

        return answerRepository.save(answerRequestDto.toEntity(survey, user)).getId();
    }

    @Override
    public Long update() {
        return null;
    }

    @Override
    public AnswerResponseDto findById() {
        return null;
    }

    @Override
    public List<SurveyListResponseDto> findAll() {
        return null;
    }

    @Override
    @Transactional
    public Long delete(Long id) {
        return id;
    }
}

package com.modu.ModuForm.app.service.survey;

import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.surbay.SurveyQuestion;
import com.modu.ModuForm.app.domain.surbay.SurveyRepository;
import com.modu.ModuForm.app.domain.surbay.answer.Answer;
import com.modu.ModuForm.app.domain.surbay.answer.AnswerRepository;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
import com.modu.ModuForm.app.web.dto.AnswerRequestDto;
import com.modu.ModuForm.app.web.dto.SurveyListResponseDto;
import com.modu.ModuForm.app.web.dto.SurveyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerServiceImpl implements AnswerService{
    private final SurveyRepository surveyRepository;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;

    @Override
    public Long save(AnswerRequestDto AnswerRequestDto, Long userId) {
        User user = userRepository.getReferenceById(userId);
        Survey survey = surveyRepository.getReferenceById(AnswerRequestDto.getSurveyId());

        return answerRepository.save(AnswerRequestDto.toEntity(survey, user)).getId();
    }

    @Override
    public Long update() {
        return null;
    }

    @Override
    public SurveyResponseDto findById() {
        return null;
    }

    @Override
    public List<SurveyListResponseDto> findAll() {
        return null;
    }

    @Override
    public Long delete(Long id) {
        return id;
    }
}

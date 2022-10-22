package com.modu.ModuForm.app.service.survey;

import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.surbay.SurveyRepository;
import com.modu.ModuForm.app.domain.surbay.answer.Answer;
import com.modu.ModuForm.app.domain.surbay.answer.AnswerRepository;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
import com.modu.ModuForm.app.service.PerfLog;
import com.modu.ModuForm.app.web.dto.answer.AnswerCheckDto;
import com.modu.ModuForm.app.web.dto.answer.AnswerResponseDto;
import com.modu.ModuForm.app.web.dto.answer.AnswerSaveDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AnswerServiceImpl implements AnswerService{
    private final SurveyRepository surveyRepository;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;

    @PerfLog
    @Override
    @Transactional
    public Long save(AnswerSaveDto answerSaveDto, Long surveyId, Long userPk) {
        User user = userRepository.getReferenceById(userPk);
        Survey survey = surveyRepository.getReferenceById(surveyId);

        return answerRepository.save(answerSaveDto.toEntity(survey, user)).getId();
    }

    @Override
    public Long update(Long answerId, AnswerSaveDto answerSaveDto) {
        Answer answer = answerRepository.findById(answerId).orElseThrow(() -> new IllegalArgumentException("해당 설문응답이 없습니다."));
        answer.update(answerSaveDto);
        return answer.getId();
    }

    @Transactional(readOnly = true)
    @Override
    public AnswerResponseDto getAnswerDto(Long id) {
        Answer answer = answerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 설문응답이 없습니다."));
        return AnswerResponseDto.builder()
                .answerId(answer.getId())
                .answerCheckDto(new AnswerCheckDto(answer.getSurvey(), answer.getAnswerDataList()))
                .anonymousFlag(answer.getAnonymousFlag())
                .build();
    }

    @Override
    @Transactional
    public Long delete(Long answerId) {
        answerRepository.delete(answerRepository.findById(answerId)
                .orElseThrow(() -> new IllegalArgumentException("해당 설문응답이 없습니다.")));
        return answerId;
    }
}

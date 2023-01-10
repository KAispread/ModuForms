package com.modu.ModuForm.app.service.survey;

import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.surbay.SurveyRepository;
import com.modu.ModuForm.app.domain.surbay.answer.Answer;
import com.modu.ModuForm.app.domain.surbay.answer.AnswerRepository;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
import com.modu.ModuForm.app.exception.nosuch.NoSuchAnswerIdException;
import com.modu.ModuForm.app.service.PerfLog;
import com.modu.ModuForm.app.web.dto.answer.AnswerCheck;
import com.modu.ModuForm.app.web.dto.answer.AnswerResponse;
import com.modu.ModuForm.app.web.dto.answer.AnswerSave;
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
    public Long save(AnswerSave answerSave, Long surveyId, Long userPk) {
        User user = userRepository.getReferenceById(userPk);
        Survey survey = surveyRepository.getReferenceById(surveyId);

        return answerRepository.save(answerSave.toEntity(survey, user)).getId();
    }

    @Override
    public Long update(Long answerId, AnswerSave answerSave) {
        Answer answer = answerRepository.findById(answerId).orElseThrow(NoSuchAnswerIdException::new);
        answer.update(answerSave);
        return answer.getId();
    }

    @Transactional(readOnly = true)
    @Override
    public AnswerResponse getAnswerDto(Long id) {
        Answer answer = answerRepository.findById(id).orElseThrow(NoSuchAnswerIdException::new);
        return AnswerResponse.builder()
                .answerId(answer.getId())
                .answerCheck(new AnswerCheck(answer.getSurvey(), answer.getAnswerDataList()))
                .anonymousFlag(answer.getAnonymousFlag())
                .build();
    }

    @Override
    @Transactional
    public Long delete(Long answerId) {
        answerRepository.delete(answerRepository.findById(answerId).orElseThrow(NoSuchAnswerIdException::new));
        return answerId;
    }
}

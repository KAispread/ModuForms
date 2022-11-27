package com.modu.ModuForm.app.service.survey;

import com.modu.ModuForm.app.web.dto.answer.AnswerResponse;
import com.modu.ModuForm.app.web.dto.answer.AnswerSave;

public interface AnswerService {
    public Long save(AnswerSave surveyAnswerRequestDto, Long surveyId, Long userPk);
    public Long update(Long answerId, AnswerSave answerSave);
    public AnswerResponse getAnswerDto(Long id);
    public Long delete(Long id);
}

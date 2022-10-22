package com.modu.ModuForm.app.service.survey;

import com.modu.ModuForm.app.web.dto.answer.AnswerResponseDto;
import com.modu.ModuForm.app.web.dto.answer.AnswerSaveDto;

public interface AnswerService {
    public Long save(AnswerSaveDto surveyAnswerRequestDto, Long surveyId, Long userPk);
    public Long update(Long answerId, AnswerSaveDto answerSaveDto);
    public AnswerResponseDto getAnswerDto(Long id);
    public Long delete(Long id);
}

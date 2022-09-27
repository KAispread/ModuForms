package com.modu.ModuForm.app.service.survey;

import com.modu.ModuForm.app.web.dto.survey.AnswerRequestDto;
import com.modu.ModuForm.app.web.dto.survey.AnswerResponseDto;
import com.modu.ModuForm.app.web.dto.survey.SurveyListResponseDto;

import java.util.List;

public interface AnswerService {
    public Long save(AnswerRequestDto surveyAnswerRequestDto,Long surveyId);
    public Long update();
    public AnswerResponseDto findById();
    public List<SurveyListResponseDto> findAll();
    public Long delete(Long id);
}

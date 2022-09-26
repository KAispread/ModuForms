package com.modu.ModuForm.app.service.survey;

import com.modu.ModuForm.app.web.dto.AnswerRequestDto;
import com.modu.ModuForm.app.web.dto.SurveyListResponseDto;
import com.modu.ModuForm.app.web.dto.SurveyResponseDto;

import java.util.List;

public interface AnswerService {
    public Long save(AnswerRequestDto surveyAnswerRequestDto, Long userId);
    public Long update();
    public SurveyResponseDto findById();
    public List<SurveyListResponseDto> findAll();
    public Long delete(Long id);
}

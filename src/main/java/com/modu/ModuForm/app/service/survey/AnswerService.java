package com.modu.ModuForm.app.service.survey;

import com.modu.ModuForm.app.web.dto.survey.AnswerSaveDto;
import com.modu.ModuForm.app.web.dto.survey.SurveyListResponseDto;
import com.modu.ModuForm.app.web.dto.user.AnswerResponseDto;

import java.util.List;

public interface AnswerService {
    public Long save(AnswerSaveDto surveyAnswerRequestDto, Long surveyId, Long userPk);
    public Long update();
    public AnswerResponseDto findById();
    public List<SurveyListResponseDto> findAll();
    public Long delete(Long id);
}

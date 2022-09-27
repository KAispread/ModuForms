package com.modu.ModuForm.app.service.survey;

import com.modu.ModuForm.app.web.dto.survey.SurveyResponseDto;

public interface SurveyService {
    public Long save();
    public Long update();
    public SurveyResponseDto findById();
}

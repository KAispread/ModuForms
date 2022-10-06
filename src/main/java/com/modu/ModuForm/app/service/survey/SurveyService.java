package com.modu.ModuForm.app.service.survey;

import com.modu.ModuForm.app.web.dto.survey.SurveyResponseDto;
import com.modu.ModuForm.app.web.dto.survey.SurveySaveDto;

public interface SurveyService {
    public Long save(SurveySaveDto surveySaveDto, String nickName);
    public Long update();
}

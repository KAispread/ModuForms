package com.modu.ModuForm.app.service.survey;

import com.modu.ModuForm.app.web.dto.survey.SurveyCheckDto;
import com.modu.ModuForm.app.web.dto.survey.SurveySaveDto;

public interface SurveyService {
    public Long save(SurveySaveDto surveySaveDto, String nickName);
    public Long update(Long id, SurveySaveDto surveySaveDto);
    public Long delete(Long id);
    public SurveyCheckDto getSurveyCheckDto(Long id);
}

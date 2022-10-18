package com.modu.ModuForm.app.service.survey;

import com.modu.ModuForm.app.web.dto.SurveyPreview;
import com.modu.ModuForm.app.web.dto.survey.SurveyCheckDto;
import com.modu.ModuForm.app.web.dto.survey.SurveySaveDto;

import java.util.List;

public interface SurveyService {
    Long save(SurveySaveDto surveySaveDto, String nickName);
    Long update(Long id, SurveySaveDto surveySaveDto);
    Long delete(Long id);
    List<SurveyPreview> findAllOrderBy();
    SurveyCheckDto getSurveyCheckDto(Long id);
}

package com.modu.ModuForm.app.service.survey;

import com.modu.ModuForm.app.web.dto.SurveyPreview;
import com.modu.ModuForm.app.web.dto.survey.SurveyAnswerCheckDto;
import com.modu.ModuForm.app.web.dto.survey.SurveyCheckDto;
import com.modu.ModuForm.app.web.dto.survey.SurveyPage;
import com.modu.ModuForm.app.web.dto.survey.SurveySaveDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface SurveyService {
    Long save(SurveySaveDto surveySaveDto, String nickName);
    Long update(Long id, SurveySaveDto surveySaveDto);
    Long delete(Long id);
    List<SurveyPreview> findAllOrderBy();
    SurveyPage findAllPages(PageRequest pageable , Integer page);
    SurveyCheckDto getSurveyCheckDto(Long id);
    SurveyAnswerCheckDto getSurveyAnswerCheckDto(Long surveyId);
}

package com.modu.ModuForm.app.service.survey;

import com.modu.ModuForm.app.web.dto.survey.SurveyAnswerCheck;
import com.modu.ModuForm.app.web.dto.survey.SurveyCheck;
import com.modu.ModuForm.app.web.dto.survey.SurveyPage;
import com.modu.ModuForm.app.web.dto.survey.SurveySave;
import org.springframework.data.domain.Pageable;

public interface SurveyService {
    Long save(SurveySave surveySave, String nickName);
    Long update(Long id, SurveySave surveySave);
    Long delete(Long id);
    SurveyPage findAllPages(Pageable pageable);
    SurveyCheck getSurveyCheckDto(Long id);
    SurveyAnswerCheck getSurveyAnswerCheckDto(Long surveyId);
}

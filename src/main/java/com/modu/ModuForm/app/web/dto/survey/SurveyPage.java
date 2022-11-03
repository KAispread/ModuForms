package com.modu.ModuForm.app.web.dto.survey;

import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.web.dto.PageContents;
import com.modu.ModuForm.app.web.dto.SurveyPreview;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SurveyPage extends PageContents {
    private List<SurveyPreview> surveyPreviews = new ArrayList<>();

    public SurveyPage(Page<Survey> surveyPage, Integer page) {
        super(surveyPage.getTotalPages(), page);
        this.surveyPreviews = surveyPage.getContent().stream()
                .map(SurveyPreview::new)
                .collect(Collectors.toList());
    }
}

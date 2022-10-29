package com.modu.ModuForm.app.web.dto.survey;

import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.web.dto.SurveyPreview;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SurveyPage {
    private final int PAGE_ROW_COUNT = 5;
    private final int currentPage;
    private List<SurveyPreview> surveyPreviews = new ArrayList<>();
    private final Integer totalPages;
    private final int startPage;
    private final int endPage;

    public SurveyPage(Page<Survey> surveyPage, Integer page) {
        this.surveyPreviews = surveyPage.getContent().stream()
                .map(SurveyPreview::new)
                .collect(Collectors.toList());
        this.totalPages = surveyPage.getTotalPages();
        this.currentPage = page;
        this.startPage = getStartPage(page);
        this.endPage = getEndPage(page);
    }

    private int getStartPage(Integer currentPage) {
        if (currentPage % PAGE_ROW_COUNT == 0) {
            return currentPage - (PAGE_ROW_COUNT - 1);
        }

        return (currentPage / PAGE_ROW_COUNT) * PAGE_ROW_COUNT + 1;
    }
    private int getEndPage(Integer currentPage) {
        int end;
        if (currentPage % PAGE_ROW_COUNT == 0) {
            return currentPage;
        }
        end = ((currentPage / PAGE_ROW_COUNT) + 1) * PAGE_ROW_COUNT;
        if (totalPages <= end) {
            return totalPages;
        }
        return end;
    }
}

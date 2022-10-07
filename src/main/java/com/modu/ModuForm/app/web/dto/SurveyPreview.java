package com.modu.ModuForm.app.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SurveyPreview {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime deadLine;
    private LocalDateTime postDate;
    private Integer answersCount;

    @Builder
    public SurveyPreview(Long id, String title, String description, LocalDateTime deadLine, LocalDateTime postDate, Integer answersCount) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
        this.postDate = postDate;
        this.answersCount = answersCount;
    }
}

package com.modu.ModuForm.app.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SurveyPreview {
    private String author;
    private Long id;
    private String title;
    private String description;
    private LocalDateTime deadLine;
    private LocalDateTime postDate;
    private Integer answersCount;

    @Builder
    public SurveyPreview(String author, Long id, String title, String description, LocalDateTime deadLine, LocalDateTime postDate, Integer answersCount) {
        this.author = author;
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
        this.postDate = postDate;
        this.answersCount = answersCount;
    }
}

package com.modu.ModuForm.app.web.dto.survey;

import lombok.Getter;

@Getter
public class DistractorCount {
    private final String distractor;
    private int count;

    public DistractorCount(String distractor) {
        this.distractor = distractor;
        this.count = 0;
    }

    public void countAdd() {
        count += 1;
    }
}

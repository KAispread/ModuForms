package com.modu.ModuForm.app.web.dto.survey;

import lombok.Getter;

@Getter
public class DistractorCount {
    private final String distractor;
    private final int count;
    private final double total;
    private double percentage;

    public DistractorCount(String distractor, int count, int total) {
        this.distractor = distractor;
        this.count = count;
        this.total = total;
        setPercentage();
    }

    private void setPercentage() {
        percentage = Math.floor(count / total * 100);
    }
}

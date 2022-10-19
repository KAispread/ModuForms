package com.modu.ModuForm.app.domain.surbay;

public enum QuesType {
    SHORT("단답형"),
    MULTIPLE("객관식");

    private final String description;
    QuesType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

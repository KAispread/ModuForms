package com.modu.ModuForm.app.exception;

public enum ErrorCode {
    USER_NOT_FOUND("40001"),
    SURVEY_NOT_FOUND("40002"),
    ANSWER_NOT_FOUND("40003"),

    INVALID_USER_USERNAME("40011"),
    INVALID_USER_ID_PW("40012"),

    NO_SUCH_USER_ID("40021"),
    NO_SUCH_SURVEY_ID("40022"),
    NO_SUCH_ANSWER_ID("40023"),

    SERVER_ERROR("50000");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

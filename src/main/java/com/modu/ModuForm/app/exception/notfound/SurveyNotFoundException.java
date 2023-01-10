package com.modu.ModuForm.app.exception.notfound;

import static com.modu.ModuForm.app.exception.ErrorCode.SURVEY_NOT_FOUND;

public class SurveyNotFoundException extends NotFoundException {

    public SurveyNotFoundException() {
        super(SURVEY_NOT_FOUND, "설문을 찾을 수 없습니다.");
    }
}

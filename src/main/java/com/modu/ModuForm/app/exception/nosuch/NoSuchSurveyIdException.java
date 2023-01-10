package com.modu.ModuForm.app.exception.nosuch;

import static com.modu.ModuForm.app.exception.ErrorCode.NO_SUCH_SURVEY_ID;

public class NoSuchSurveyIdException extends NoSuchValueException {

    public NoSuchSurveyIdException() {
        super(NO_SUCH_SURVEY_ID, "해당 설문이 존재하지 않습니다.");
    }
}

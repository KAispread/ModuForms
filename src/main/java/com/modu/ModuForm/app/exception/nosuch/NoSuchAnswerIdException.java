package com.modu.ModuForm.app.exception.nosuch;

import static com.modu.ModuForm.app.exception.ErrorCode.NO_SUCH_ANSWER_ID;

public class NoSuchAnswerIdException extends NoSuchValueException {

    public NoSuchAnswerIdException() {
        super(NO_SUCH_ANSWER_ID, "해당 응답이 존재하지 않습니다.");
    }
}

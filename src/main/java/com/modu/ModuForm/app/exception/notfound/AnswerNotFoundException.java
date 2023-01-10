package com.modu.ModuForm.app.exception.notfound;

import static com.modu.ModuForm.app.exception.ErrorCode.ANSWER_NOT_FOUND;

public class AnswerNotFoundException extends NotFoundException {

    public AnswerNotFoundException() {
        super(ANSWER_NOT_FOUND, "응답을 찾을 수 없습니다.");
    }
}

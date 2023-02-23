package com.modu.ModuForm.app.domain.surbay.answer;

import com.modu.ModuForm.app.web.dto.answer.AnswerResponse;

public interface AnswerRepositoryCustom {
    AnswerResponse getAnswerResponse(Long id);
}

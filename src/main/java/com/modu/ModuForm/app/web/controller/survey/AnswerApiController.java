package com.modu.ModuForm.app.web.controller.survey;

import com.modu.ModuForm.app.service.survey.AnswerServiceImpl;
import com.modu.ModuForm.app.web.config.auth.LoginUser;
import com.modu.ModuForm.app.web.config.dto.JwtUser;
import com.modu.ModuForm.app.web.dto.answer.AnswerSave;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Api(tags = "Answer DATA handling API")
@RequiredArgsConstructor
@RequestMapping("/app/answers")
@RestController
public class AnswerApiController {
    private final AnswerServiceImpl answerService;

    @Operation(summary = "응답 저장 API", description = "응답 저장 요청을 처리합니다.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Long save(@AuthenticationPrincipal JwtUser user, @Validated @RequestBody AnswerSave answerSave, @RequestParam Long surveyId) {
        Long userId = user.getId();
        return answerService.save(answerSave, surveyId, userId);
    }

    @Operation(summary = "응답 수정 API", description = "응답 수정 요청을 처리합니다.")
    @PutMapping("/{answerId}")
    public Long update(@Validated @RequestBody AnswerSave answerSave, @PathVariable Long answerId) {
        return answerService.update(answerId, answerSave);
    }

    @Operation(summary = "응답 삭제 API", description = "응답 삭제 요청을 처리합니다.")
    @DeleteMapping("/{answerId}")
    public Long delete(@PathVariable Long answerId) {
        return answerService.delete(answerId);
    }
}

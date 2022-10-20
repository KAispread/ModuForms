package com.modu.ModuForm.app.web.controller.survey;

import com.modu.ModuForm.app.service.survey.AnswerServiceImpl;
import com.modu.ModuForm.app.web.config.SessionManager;
import com.modu.ModuForm.app.web.dto.survey.AnswerSaveDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Api(tags = "Answer DATA handling API")
@RequiredArgsConstructor
@RequestMapping("/app")
@RestController
public class AnswerApiController {
    private final AnswerServiceImpl answerService;
    private final SessionManager sessionManager;

    // 설문 응답 제출
    @PostMapping("/{surveyId}/answer")
    public Long save(@RequestBody AnswerSaveDto answerSaveDto, @PathVariable Long surveyId, HttpServletRequest request) {
        Long userPk = sessionManager.getUserPk(request);
        return answerService.save(answerSaveDto, surveyId, userPk);
    }

    // 선택한 응답 수정
    @PutMapping("/{surveyId}/answer")
    public Long update(@PathVariable Long surveyId) {
        return answerService.update();
    }

    // 선택한 응답 삭제
    @DeleteMapping
    public Long delete(@PathVariable Long id) {
        return answerService.delete(id);
    }

}

package com.modu.ModuForm.app.web.controller.survey;

import com.modu.ModuForm.app.service.survey.AnswerService;
import com.modu.ModuForm.app.service.survey.SurveyService;
import com.modu.ModuForm.app.web.dto.answer.AnswerStringClass;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/answers")
@Controller
public class AnswerIndexController {
    private final AnswerService answerService;
    private final SurveyService surveyService;

    @Operation(summary = "설문 응답 페이지", description = "선택한 설문에 대해 응답하는 페이지을 반환합니다")
    @GetMapping
    public String answerForm(@RequestParam Long surveyId, Model model) {
        model.addAttribute("surveyCheck", surveyService.getSurveyCheckDto(surveyId));
        model.addAttribute("answer", new AnswerStringClass());
        return "/answer/answer";
    }

    @Operation(summary = "응답 확인 페이지", description = "선택한 응답을 확인하는 페이지를 반환합니다.")
    @GetMapping("/{answerId}")
    public String check(@PathVariable Long answerId, Model model) {
        model.addAttribute("answerDto", answerService.getAnswerDto(answerId));
        return "/answer/answer-check";
    }

    @Operation(summary = "응답 수정 페이지", description = "선택한 응답을 수정하는 페이지를 반환합니다.")
    @GetMapping("/{answerId}/edit")
    public String edit(@PathVariable Long answerId, Model model) {
        model.addAttribute("answerDto", answerService.getAnswerDto(answerId));
        return "/answer/answer-edit";
    }
}

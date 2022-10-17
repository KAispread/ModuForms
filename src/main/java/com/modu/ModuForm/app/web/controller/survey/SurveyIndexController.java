package com.modu.ModuForm.app.web.controller.survey;

import com.modu.ModuForm.app.service.survey.SurveyService;
import com.modu.ModuForm.app.web.dto.survey.SurveyCheckDto;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Survey TEMPLATE handling")
@RequestMapping("/surveys")
@RequiredArgsConstructor
@Controller
public class SurveyIndexController {
    private final SurveyService surveyService;

    @Operation(summary = "설문 등록 페이지", description = "설문을 등록하는 페이지입니다.")
    @GetMapping("/form")
    public String register(){
        return "survey/formTemplate";
    }

    @Operation(summary = "설문 확인 페이지", description = "선택한 설문을 확인합니다.")
    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        SurveyCheckDto surveyCheckDto = surveyService.getSurveyCheckDto(id);
        model.addAttribute("surveyCheck", surveyCheckDto);
        return "/survey/form-check";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        SurveyCheckDto surveyCheckDto = surveyService.getSurveyCheckDto(id);
        model.addAttribute("surveyCheck", surveyCheckDto);
        return "/survey/form-edit";
    }
}

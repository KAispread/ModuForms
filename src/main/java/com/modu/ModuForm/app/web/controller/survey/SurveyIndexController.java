package com.modu.ModuForm.app.web.controller.survey;

import com.modu.ModuForm.app.domain.surbay.QuesType;
import com.modu.ModuForm.app.service.survey.SurveyService;
import com.modu.ModuForm.app.web.dto.survey.AnswerStringClass;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "Survey TEMPLATE handling")
@RequestMapping("/surveys")
@RequiredArgsConstructor
@Controller
public class SurveyIndexController {
    private final SurveyService surveyService;
    private final QuesType[] quesTypes = QuesType.values();
    @ModelAttribute("quesTypes")
    public QuesType[] quesTypes() {
        return quesTypes;
    }

    @Operation(summary = "설문 등록 페이지", description = "설문을 등록하는 페이지입니다.")
    @GetMapping("/form")
    public String register(){
        return "survey/formTemplate";
    }

    @Operation(summary = "설문 확인 페이지", description = "선택한 설문을 확인합니다.")
    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("surveyCheck", surveyService.getSurveyCheckDto(id));
        return "/survey/form-check";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("surveyCheck", surveyService.getSurveyCheckDto(id));
        return "/survey/form-edit";
    }

    @GetMapping("/list")
    public String formList(Model model) {
        model.addAttribute("surveyPreviewList", surveyService.findAllOrderBy());
        return "/survey/formList";
    }

    @GetMapping("/{id}/answer")
    public String answerForm(@PathVariable Long id, Model model) {
        model.addAttribute("surveyCheck", surveyService.getSurveyCheckDto(id));
        model.addAttribute("answer", new AnswerStringClass());
        return "/survey/form-answer";
    }
}

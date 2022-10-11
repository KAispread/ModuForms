package com.modu.ModuForm.app.web.controller.survey;

import com.modu.ModuForm.app.service.survey.SurveyService;
import com.modu.ModuForm.app.service.user.UserService;
import com.modu.ModuForm.app.web.config.SessionManager;
import com.modu.ModuForm.app.web.dto.survey.SurveyCheckDto;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api(tags = "Survey TEMPLATE handling")
@RequestMapping("/surveys")
@RequiredArgsConstructor
@Controller
public class SurveyIndexController {
    private final SurveyService surveyService;
    private final SessionManager sessionManager;
    private final UserService userService;

    @Operation(summary = "설문조사 양식 - 1", description = "ModuForm에서 제공하는 설문조사 양식입니다. -> 1")
    @GetMapping("/form/1")
    public String registerPage1(){
        return "/survey/formTemplate1";
    }

    @Operation(summary = "설문 확인 페이지", description = "선택한 설문을 확인합니다.")
    @GetMapping("/{id}")
    public String viewSurvey(@PathVariable Long id, Model model) {
        SurveyCheckDto surveyCheckDto = surveyService.getSurveyCheckDto(id);
        model.addAttribute("surveyCheck", surveyCheckDto);
        return "/survey/form-check";
    }
}

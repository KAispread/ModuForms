package com.modu.ModuForm.app.web.controller.survey;

import com.modu.ModuForm.app.service.survey.SurveyService;
import com.modu.ModuForm.app.service.user.UserService;
import com.modu.ModuForm.app.web.config.SessionManager;
import com.modu.ModuForm.app.web.dto.survey.SurveyCheckDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/survey")
@RequiredArgsConstructor
@Controller
public class SurveyIndexController {
    private final SurveyService surveyService;
    private final SessionManager sessionManager;
    private final UserService userService;

    @GetMapping("/register/1")
    public String registerPage1(Model model, HttpServletRequest request, HttpSession session){
        return "/survey/formTemplate1";
    }

    @GetMapping("/{id}")
    public String viewSurvey(@PathVariable Long id, Model model) {
        SurveyCheckDto surveyCheckDto = surveyService.getSurveyCheckDto(id);
        model.addAttribute("surveyCheck", surveyCheckDto);
        return "/survey/form-check";
    }
}

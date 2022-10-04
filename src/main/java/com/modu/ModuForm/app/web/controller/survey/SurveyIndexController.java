package com.modu.ModuForm.app.web.controller.survey;

import com.modu.ModuForm.app.service.survey.SurveyService;
import com.modu.ModuForm.app.web.config.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/survey")
@RequiredArgsConstructor
@Controller
public class SurveyIndexController {
//    private final SurveyService surveyService;
    private final SessionManager sessionManager;

}

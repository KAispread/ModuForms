package com.modu.ModuForm.app.web.controller.survey;

import com.modu.ModuForm.app.service.survey.SurveyService;
import com.modu.ModuForm.app.service.survey.SurveyServiceImpl;
import com.modu.ModuForm.app.web.dto.survey.SurveySaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/app/survey")
@RequiredArgsConstructor
@RestController
public class SurveyApiController {
    private final SurveyService surveyService;

    @PostMapping("/{nickName}")
    public Long save(@RequestBody SurveySaveDto surveySaveDto, @PathVariable String nickName) {
        return surveyService.save(surveySaveDto, nickName);
    }

}

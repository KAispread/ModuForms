package com.modu.ModuForm.app.web.controller.survey;

import com.modu.ModuForm.app.domain.surbay.QuesType;
import com.modu.ModuForm.app.service.survey.SurveyService;
import com.modu.ModuForm.app.web.config.auth.dto.JwtUser;
import com.modu.ModuForm.app.web.config.auth.jwt.JwtHandler;
import com.modu.ModuForm.app.web.dto.survey.SurveyPage;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "Survey TEMPLATE handling")
@RequestMapping("/surveys")
@RequiredArgsConstructor
@Controller
public class SurveyIndexController {
    private final SurveyService surveyService;
    private final JwtHandler jwtHandler;
    private final QuesType[] quesTypes = QuesType.values();

    @ModelAttribute("quesTypes")
    public QuesType[] quesTypes() {
        return quesTypes;
    }

    @Operation(summary = "설문 등록 페이지", description = "설문을 등록하는 페이지를 반환합니다.")
    @GetMapping("/form")
    public String register(Model model, HttpServletRequest request){
        JwtUser user = jwtHandler.getJwtUser(request);
        model.addAttribute("user", user);
        return "survey/formTemplate";
    }

    @Operation(summary = "설문 확인 페이지", description = "선택한 설문을 페이지를 반환합니다.")
    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model, HttpServletRequest request) {
        JwtUser user = jwtHandler.getJwtUser(request);
        model.addAttribute("user", user);
        model.addAttribute("surveyCheck", surveyService.getSurveyCheckDto(id));
        return "survey/form-check";
    }

    @Operation(summary = "설문 수정 페이지", description = "선택한 설문을 수정하는 페이지를 반환합니다.")
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model, HttpServletRequest request) {
        JwtUser user = jwtHandler.getJwtUser(request);
        model.addAttribute("user", user);
        model.addAttribute("surveyCheck", surveyService.getSurveyCheckDto(id));
        return "survey/form-edit";
    }

    @Operation(summary = "설문 목록 페이지", description = "모든 설문을 수정하는 페이지를 반환합니다.")
    @GetMapping("/lists")
    public String formList(Model model, @RequestParam(name = "sp", defaultValue = "1") Integer page, HttpServletRequest request) {
        JwtUser user = jwtHandler.getJwtUser(request);

        PageRequest pageRequest = PageRequest.of(page - 1, 9, Sort.by("createdDate").descending());
        SurveyPage surveyPages = surveyService.findAllPages(pageRequest, page);

        model.addAttribute("user", user);
        model.addAttribute("surveyPage", surveyPages);
        return "survey/formPage";
    }

    @Operation(summary = "설문 응답 확인 페이지", description = "선택한 설문에 대한 응답을 확인하는 페이지를 반환합니다.")
    @GetMapping("/{surveyId}/answer")
    public String formResult(@PathVariable Long surveyId, Model model, HttpServletRequest request) {
        JwtUser user = jwtHandler.getJwtUser(request);
        model.addAttribute("user", user);
        model.addAttribute("queAnsList", surveyService.getSurveyAnswerCheckDto(surveyId));
        return "survey/form-check-Answer";
    }
}

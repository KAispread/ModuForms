package com.modu.ModuForm.app.web.controller;

import com.modu.ModuForm.app.service.user.UserService;
import com.modu.ModuForm.app.web.config.SessionManager;
import com.modu.ModuForm.app.web.dto.user.UserFormDetailsDto;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api(tags = "MainPage TEMPLATE")
@RequiredArgsConstructor
@Controller
public class MainPageController {
    private final SessionManager sessionManager;
    private final UserService userService;
    @Operation(summary = "메인 페이지", description = "메인 페이지를 반환합니다. 로그인을 하지 않았거나 세션이 만료되었다면 로그인 페이지를 반환합니다.")
    @GetMapping("/")
    public String mainPage(HttpServletRequest request, Model model,
                           @RequestParam(name = "sp", defaultValue = "1") Integer surveyPage,
                           @RequestParam(name = "ap", defaultValue = "1") Integer answerPage) {
        Long userPk = sessionManager.getUserPk(request);
        HttpSession session = request.getSession();
        if(session == null || userPk == null) {
            return "redirect:/users/login";
        }

        PageRequest surveyPageRequest = PageRequest.of(surveyPage - 1, 9, Sort.by("createdDate").descending());
        PageRequest answerPageRequest = PageRequest.of(answerPage - 1, 9, Sort.by("createdDate").descending());
        UserFormDetailsDto userMainDetails = userService.getUserFormDetails(surveyPageRequest, surveyPage, answerPageRequest, answerPage, userPk);

        model.addAttribute("userFormDetails", userMainDetails);
        return "userMain";
    }
}

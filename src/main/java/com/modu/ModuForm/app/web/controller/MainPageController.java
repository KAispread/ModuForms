package com.modu.ModuForm.app.web.controller;

import com.modu.ModuForm.app.service.user.UserService;
import com.modu.ModuForm.app.web.config.auth.LoginUser;
import com.modu.ModuForm.app.web.config.auth.dto.SessionUser;
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

@Api(tags = "MainPage TEMPLATE")
@RequiredArgsConstructor
@Controller
public class MainPageController {
    private final UserService userService;
    @Operation(summary = "메인 페이지", description = "메인 페이지를 반환합니다.")
    @GetMapping("/")
    public String mainPage(Model model, @LoginUser SessionUser user,
                           @RequestParam(name = "sp", defaultValue = "1") Integer surveyPage,
                           @RequestParam(name = "ap", defaultValue = "1") Integer answerPage) {
        Long userid = user.getId();

        PageRequest surveyPageRequest = PageRequest.of(surveyPage - 1, 9, Sort.by("createdDate").descending());
        PageRequest answerPageRequest = PageRequest.of(answerPage - 1, 9, Sort.by("createdDate").descending());
        UserFormDetailsDto userMainDetails = userService.getUserFormDetails(surveyPageRequest, surveyPage, answerPageRequest, answerPage, userid);

        model.addAttribute("userFormDetails", userMainDetails);
        return "userMain";
    }
}

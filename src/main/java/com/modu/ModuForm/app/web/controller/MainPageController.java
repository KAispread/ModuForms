package com.modu.ModuForm.app.web.controller;

import com.modu.ModuForm.app.service.MainPageService;
import com.modu.ModuForm.app.web.config.auth.LoginUser;
import com.modu.ModuForm.app.web.config.auth.dto.JwtUser;
import com.modu.ModuForm.app.web.dto.user.UserFormDetails;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Api(tags = "MainPage TEMPLATE")
@RequiredArgsConstructor
@Controller
public class MainPageController {
    private final MainPageService mainPageService;

    @Operation(summary = "메인 페이지", description = "메인 페이지를 반환합니다.")
    @GetMapping("/")
    public String mainPage(Model model, @LoginUser JwtUser user,
                           @Qualifier("survey") @PageableDefault(size = 9, sort = "createdDate", direction = DESC) Pageable surveyPage,
                           @Qualifier("answer") @PageableDefault(size = 9, sort = "createdDate", direction = DESC) Pageable answerPage) {
        Long userid = user.getId();
        UserFormDetails userMainDetails = mainPageService.getUserFormDetails(surveyPage, answerPage, userid);

        model.addAttribute("userFormDetails", userMainDetails);
        return "user/userMain";
    }
}

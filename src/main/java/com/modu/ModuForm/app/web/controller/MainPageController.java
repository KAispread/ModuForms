package com.modu.ModuForm.app.web.controller;

import com.modu.ModuForm.app.service.user.UserService;
import com.modu.ModuForm.app.web.config.SessionManager;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String mainPage(HttpServletRequest request, Model model) {
        Long userPk = sessionManager.getUserPk(request);
        HttpSession session = request.getSession();
        if(session == null || userPk == null) {
            return "redirect:/users/login";
        }

        model.addAttribute("userFormDetails", userService.getUserFormDetails(userPk));
        return "userMain";
    }
}

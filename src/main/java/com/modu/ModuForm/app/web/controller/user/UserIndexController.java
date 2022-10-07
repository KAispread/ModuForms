package com.modu.ModuForm.app.web.controller.user;

import com.modu.ModuForm.app.domain.user.Access;
import com.modu.ModuForm.app.service.user.UserService;
import com.modu.ModuForm.app.web.config.SessionManager;
import com.modu.ModuForm.app.web.dto.user.LoginRequestDto;
import com.modu.ModuForm.app.web.dto.user.UserSubDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@RequestMapping("basic/")
@RequiredArgsConstructor
@Controller
public class UserIndexController {
    private final UserService userService;
    private final SessionManager sessionManager;

    @GetMapping("/login")
    public String userLogin() {
        return "loginForm";
    }

    @PostMapping("/login")
    public String login(LoginRequestDto loginRequestDto, BindingResult bindingResult, HttpSession session, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "loginForm";
        }
        Access loginAccess = userService.login(loginRequestDto);
        if (loginAccess == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "loginForm";
        }
        String nickName = sessionManager.createSession(session, loginAccess, response);

        return "redirect:/" + nickName;
    }

    @GetMapping("/")
    public String mainPage(HttpServletRequest request, HttpSession session) {
        Long userPk = sessionManager.getSession(request, session);
        if(userPk == null) {
            return "loginForm";
        }

        return "redirect:/" + session.getAttribute("userNickName");
    }

    @GetMapping("/{nickName}")
    public String userMain(Model model , @PathVariable String nickName, HttpServletRequest request, HttpSession session) {
        Long userPk = sessionManager.getSession(request, session);
        if(userPk == null) {
            return "loginForm";
        }

        model.addAttribute("userSubDetails", userService.getUserSubDetails(userPk));
        return "userMain";
    }

    @GetMapping()
    public String surveySave() {
        return "survey-save";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    //////// Lecture
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/users/{id}")
    public String detail(@PathVariable Long id) {
        userService.getUserDetails(id);

        return "userDetail";
    }
}

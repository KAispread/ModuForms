package com.modu.ModuForm.app.web.controller.user;

import com.modu.ModuForm.app.domain.user.Access;
import com.modu.ModuForm.app.domain.user.Gender;
import com.modu.ModuForm.app.service.user.UserService;
import com.modu.ModuForm.app.web.config.SessionManager;
import com.modu.ModuForm.app.web.dto.user.LoginRequestDto;
import com.modu.ModuForm.app.web.dto.user.UserRegisterDto;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "User TEMPLATE handling")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
@Controller
public class UserIndexController {
    private final UserService userService;
    private final SessionManager sessionManager;
    private final Gender[] genders = Gender.values();

    @Operation(summary = "로그인 페이지", description = "로그인 템플릿을 반환합니다.")
    @GetMapping("/login")
    public String userLogin() {
        return "loginForm";
    }

    @Operation(summary = "로그인 요청 처리", description = "로그인 요청을 처리합니다.")
    @PostMapping("/login")
    public String login(LoginRequestDto loginRequestDto, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "loginForm";
        }
        Access loginAccess = userService.login(loginRequestDto);
        if (loginAccess == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "loginForm";
        }

        log.info("{}: login application", sessionManager.createSession(request, loginAccess));
        return "redirect:/";
    }

    @Operation(summary = "회원가입 페이지", description = "회원가입 페이지를 반환합니다.")
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userRegisterDto", new UserRegisterDto());
        model.addAttribute("genders", genders);
        return "register";
    }

    @Operation(summary = "프로필 조회", description = "로그인 템플릿을 반환합니다.")
    @GetMapping("/{nickName}")
    public String detail(@PathVariable Long id) {
        userService.getUserDetails(id);

        return "userDetail";
    }

    @GetMapping("/logout")
    public String reLogin() {
        return "redirect:/users/login";
    }
}

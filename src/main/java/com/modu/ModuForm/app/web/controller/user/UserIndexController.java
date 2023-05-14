package com.modu.ModuForm.app.web.controller.user;

import com.modu.ModuForm.app.domain.user.common.Gender;
import com.modu.ModuForm.app.service.user.UserService;
import com.modu.ModuForm.app.web.config.auth.LoginUser;
import com.modu.ModuForm.app.web.config.dto.JwtUser;
import com.modu.ModuForm.app.web.dto.user.LoginRequest;
import com.modu.ModuForm.app.web.dto.user.UserDetails;
import com.modu.ModuForm.app.web.dto.user.UserRegister;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Api(tags = "User TEMPLATE handling")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
@Controller
public class UserIndexController {
    private final UserService userService;

    @ModelAttribute("genders")
    public Gender[] genders() {
        return Gender.values();
    }

    @Operation(summary = "로그인 페이지", description = "로그인 페이지를 반환합니다.")
    @GetMapping("/login")
    public String userLogin(Model model, @RequestParam(required = false) String error, BindingResult bindingResult) {
        model.addAttribute("login", new LoginRequest());

        if (StringUtils.hasText(error)) {
            bindingResult.reject("login", null, "아이디나 비밀번호가 맞지 않습니다.");
        }

        return "user/loginForm";
    }

    @Operation(summary = "회원가입 페이지", description = "회원가입 페이지를 반환합니다.")
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userRegister", new UserRegister());
        return "user/register";
    }

    @Operation(summary = "프로필 조회", description = "선택한 유저의 프로필 페이지를 반환합니다.")
    @GetMapping("/{userId}")
    public String detail(@PathVariable Long userId, @AuthenticationPrincipal JwtUser user, Model model) {
        UserDetails userDetails = userService.getUserDetails(userId);

        model.addAttribute("user", user);
        model.addAttribute("userDetails", userDetails);

        return "user/userDetail";
    }

    @GetMapping("/{userId}/edit")
    public String edit(@PathVariable Long userId, @AuthenticationPrincipal JwtUser user, Model model) {
        UserDetails userDetails = userService.getUserDetails(userId);

        model.addAttribute("user", user);
        model.addAttribute("userDetails", userDetails);

        return "user/userEdit";
    }

    @GetMapping("/logout")
    public String reLogin() {
        return "redirect:/users/login";
    }
}

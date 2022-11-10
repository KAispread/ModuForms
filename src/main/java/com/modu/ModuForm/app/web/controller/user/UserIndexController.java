package com.modu.ModuForm.app.web.controller.user;

import com.modu.ModuForm.app.domain.user.Gender;
import com.modu.ModuForm.app.service.user.UserService;
import com.modu.ModuForm.app.web.dto.user.UserRegisterDto;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "User TEMPLATE handling")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
@Controller
public class UserIndexController {
    private final UserService userService;
    private final Gender[] genders = Gender.values();

    @Operation(summary = "로그인 페이지", description = "로그인 페이지를 반환합니다.")
    @GetMapping("/login")
    public String userLogin() {
        return "loginForm";
    }

    @Operation(summary = "회원가입 페이지", description = "회원가입 페이지를 반환합니다.")
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userRegisterDto", new UserRegisterDto());
        model.addAttribute("genders", genders);
        return "register";
    }

    @Operation(summary = "프로필 조회", description = "선택한 유저의 프로필 페이지를 반환합니다.")
    @GetMapping("/{nickName}")
    public String detail(@PathVariable Long nickName) {
        userService.getUserDetails(nickName);

        return "userDetail";
    }

    @GetMapping("/logout")
    public String reLogin() {
        return "redirect:/users/login";
    }
}

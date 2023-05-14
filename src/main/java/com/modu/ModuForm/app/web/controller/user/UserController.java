package com.modu.ModuForm.app.web.controller.user;

import com.modu.ModuForm.app.domain.user.common.Gender;
import com.modu.ModuForm.app.service.user.UserAccountService;
import com.modu.ModuForm.app.service.user.UserServiceImpl;
import com.modu.ModuForm.app.web.dto.user.UserDetails;
import com.modu.ModuForm.app.web.dto.user.UserRegister;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Api(tags = "User DATA handling API")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/app/users")
@Controller
public class UserController {
    private final UserServiceImpl userService;
    private final UserAccountService userAccountService;

    @Operation(summary = "회원가입", description = "회원가입 요청을 처리합니다.")
    @PostMapping
    public String register(@Validated @ModelAttribute UserRegister userRegister, BindingResult bindingResult,
                           Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.warn("error={}", bindingResult);
            model.addAttribute("userRegister", userRegister);
            model.addAttribute("genders", Gender.values());
            return "user/register";
        }
        userService.register(userRegister);
        redirectAttributes.addAttribute("created", true);
        return "redirect:/users/login";
    }

    @Operation(summary = "로그아웃 요청 처리", description = "로그아웃 요청을 처리합니다.")
    @PostMapping("/logout")
    public void logoutJwt(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("AuthAT", null);
        response.setHeader("AuthRT", null);

        SecurityContextHolder.clearContext();
    }

    // 회원 정보 수정
    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정합니다.")
    @ResponseBody
    @PatchMapping("/{userId}")
    public Long update(@Validated @RequestBody UserDetails userDetails, @PathVariable Long userId) {
        return userService.update(userDetails, userId);
    }

    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴 요청을 처리합니다.")
    @ResponseBody
    @DeleteMapping("/{userId}")
    public void delete(@PathVariable Long userId) {
        userAccountService.delete(userId);
    }
}

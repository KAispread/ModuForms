package com.modu.ModuForm.app.web.controller.user;

import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.service.user.UserAccountService;
import com.modu.ModuForm.app.service.user.UserServiceImpl;
import com.modu.ModuForm.app.web.config.auth.jwt.JwtHandler;
import com.modu.ModuForm.app.web.dto.user.LoginRequest;
import com.modu.ModuForm.app.web.dto.user.UserDetails;
import com.modu.ModuForm.app.web.dto.user.UserRegister;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.modu.ModuForm.app.web.config.auth.jwt.JwtCookie.ENCRYPT;
import static com.modu.ModuForm.app.web.config.auth.jwt.JwtCookie.NORMAL;

@Api(tags = "User DATA handling API")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/app/users")
@Controller
public class UserController {
    private final UserServiceImpl userService;
    private final UserAccountService userAccountService;
    private final JwtHandler jwtHandler;

    // 회원가입
    @Operation(summary = "회원가입", description = "회원가입 요청을 처리합니다.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String register(@Validated @RequestBody UserRegister userRegister) {
        userService.register(userRegister);
        return "user/loginForm";
    }

    @Operation(summary = "JWT 로그인 요청 처리", description = "JWT 로그인 요청을 처리합니다.")
    @PostMapping("/logins")
    public String loginJwt(@Validated @ModelAttribute(name = "login") LoginRequest loginRequest, BindingResult bindingResult,
                           @RequestParam(defaultValue = "/") String redirectURL,
                           HttpServletResponse response) throws IOException {
        if (bindingResult.hasErrors()) {
            log.warn("error={}", bindingResult);
            return "user/loginForm";
        }

        User user;
        try {
            user = userService.login(loginRequest).getUser();
        } catch (IllegalArgumentException argumentException) {
            bindingResult.reject("login", null, "아이디나 비밀번호가 맞지 않습니다.");
            log.warn("login error={}", bindingResult);
            return "user/loginForm";
        }

        response.addCookie(jwtHandler.createJwtCookie(user));
        response.addCookie(jwtHandler.createEncryptJwtCookie(user));
        return "redirect:" + redirectURL;
    }

    @Operation(summary = "JWT 로그아웃 요청 처리", description = "JWT 로그아웃 요청을 처리합니다.")
    @PostMapping("/logouts")
    public void logoutJwt(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            log.warn("No Cookies available");
            return;
        }
        jwtHandler.invalidate(ENCRYPT, response);
        jwtHandler.invalidate(NORMAL, response);
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

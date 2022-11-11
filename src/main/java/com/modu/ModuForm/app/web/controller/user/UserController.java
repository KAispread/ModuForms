package com.modu.ModuForm.app.web.controller.user;

import com.modu.ModuForm.app.domain.user.Access;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.service.user.UserServiceImpl;
import com.modu.ModuForm.app.web.config.auth.SessionManager;
import com.modu.ModuForm.app.web.config.auth.jwt.JwtHandler;
import com.modu.ModuForm.app.web.dto.user.LoginRequestDto;
import com.modu.ModuForm.app.web.dto.user.UserRegisterDto;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.modu.ModuForm.app.web.config.auth.jwt.JwtCookie.ENCRYPT;
import static com.modu.ModuForm.app.web.config.auth.jwt.JwtCookie.NORMAL;

@Api(tags = "User DATA handling API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/users")
public class UserController {
    private final UserServiceImpl userService;
    private final SessionManager sessionManager;
    private final JwtHandler jwtHandler;

    // 회원가입
    @Operation(summary = "회원가입", description = "회원가입 요청을 처리합니다.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Long register(@Validated @RequestBody UserRegisterDto userRegisterDto) {
        return userService.register(userRegisterDto);
    }

    @Operation(summary = "로그인 요청 처리", description = "로그인 요청을 처리합니다.")
    @PostMapping("/login")
    public Long login(@Validated LoginRequestDto loginRequestDto, BindingResult bindingResult,
                      @RequestParam(defaultValue = "/") String redirectURL,
                      HttpServletResponse response) throws IOException {
        if (bindingResult.hasErrors()) {
            log.warn("바인딩 에러");
            response.sendRedirect("/users/login");
            return 0L;
        }

        Access loginAccess = userService.login(loginRequestDto);
        log.info("{}: USER LOGIN", sessionManager.createSession(loginAccess));

        response.sendRedirect(redirectURL);
        return loginAccess.getId();
    }

    @Operation(summary = "JWT 로그인 요청 처리", description = "JWT 로그인 요청을 처리합니다.")
    @PostMapping("/logins")
    public Long loginJwt(@Validated LoginRequestDto loginRequestDto, BindingResult bindingResult,
                      @RequestParam(defaultValue = "/") String redirectURL, HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        if (bindingResult.hasErrors()) {
            log.warn("BINDING ERROR");
            response.sendRedirect("/users/login");
            return 0L;
        }

        User user = userService.login(loginRequestDto).getUser();

        response.addCookie(jwtHandler.createJwtCookie(user));
        response.addCookie(jwtHandler.createEncryptJwtCookie(user));

        response.sendRedirect(redirectURL);
        return user.getId();
    }

    @Operation(summary = "로그아웃 요청 처리", description = "로그아웃 요청을 처리합니다.")
    @PostMapping("/logout")
    public Long logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            log.warn("No Session available");
            return 0L;
        }

        Long userPk = (Long) session.getAttribute("userPk");
        session.invalidate();
        return userPk;
    }

    @Operation(summary = "JWT 로그아웃 요청 처리", description = "JWT 로그아웃 요청을 처리합니다.")
    @PostMapping("/logouts")
    public void logoutJwt(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            log.warn("No Cookies available");
            return;
        }
        jwtHandler.invalidate(ENCRYPT.getCookieName(), response);
        jwtHandler.invalidate(NORMAL.getCookieName(), response);
    }

    // 회원 정보 수정
    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정합니다.")
    @PatchMapping("/{nickname}")
    public Long update(@Validated @RequestBody UserRegisterDto userRequestDto, @PathVariable String nickname) {
        return null;
    }

    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴 요청을 처리합니다.")
    @DeleteMapping("/{nickname}")
    public Long delete(@PathVariable String nickname) {
        return null;
    }
}

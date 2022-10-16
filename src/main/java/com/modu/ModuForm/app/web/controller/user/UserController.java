package com.modu.ModuForm.app.web.controller.user;

import com.modu.ModuForm.app.service.user.UserServiceImpl;
import com.modu.ModuForm.app.web.dto.user.UserRegisterDto;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api(tags = "User DATA handling API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/users")
public class UserController {
    private final UserServiceImpl userService;

    // 회원가입
    @Operation(summary = "회원가입", description = "회원가입 요청을 처리합니다.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Long register(@RequestBody UserRegisterDto userRegisterDto) {
        return userService.register(userRegisterDto);
    }

    @Operation(summary = "로그아웃 요청 처리", description = "로그아웃 요청을 처리합니다.")
    @PostMapping("/logout")
    public Long logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return 0L;
        }

        Long userPk = (Long) session.getAttribute("userPk");
        log.info("{}: logout application", session.getAttribute("userNickName"));
        session.invalidate();

        return userPk;
    }

    // 회원 정보 수정
    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정합니다.")
    @PatchMapping("/{nickname}")
    public Long update(@PathVariable String nickname, @RequestBody UserRegisterDto userRequestDto) {
        return null;
    }

    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴 요청을 처리합니다.")
    @DeleteMapping("/{nickname}")
    public Long delete(@PathVariable String nickname) {
        return null;
    }
}

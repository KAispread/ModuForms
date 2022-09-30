package com.modu.ModuForm.app.web.controller.user;

import com.modu.ModuForm.app.service.user.UserServiceImpl;
import com.modu.ModuForm.app.web.dto.user.AdminRequestDto;
import com.modu.ModuForm.app.web.dto.user.LoginRequestDto;
import com.modu.ModuForm.app.web.dto.user.UserRequestDto;
import com.modu.ModuForm.app.web.dto.user.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserServiceImpl userService;
    // 로그인
    @PostMapping("/app/user/login")
    public UserResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return userService.login(loginRequestDto);
    }

    // 회원가입
    @PostMapping("/app/user")
    public Long signUp(@RequestBody UserRequestDto userRequestDto) {
        return null;
    }

    // 회원 정보 수정
    @PutMapping("/app/user/{id}")
    public Long update(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {
        return null;
    }

    // 관리자 계정 생성
    @PostMapping("/app/user/{id}/admin")
    public Long createAdmin(@PathVariable Long id, @RequestBody AdminRequestDto adminRequestDto) {
        return null;
    }

    // 회원 정보 보기
    @GetMapping("/app/user/{id}")
    public UserResponseDto viewProfile(@PathVariable Long id) {
        return null;
    }
}

package com.modu.ModuForm.app.web.controller.user;

import com.modu.ModuForm.app.domain.user.Access;
import com.modu.ModuForm.app.service.user.UserServiceImpl;
import com.modu.ModuForm.app.web.dto.user.AdminRequestDto;
import com.modu.ModuForm.app.web.dto.user.LoginRequestDto;
import com.modu.ModuForm.app.web.dto.user.UserRegisterDto;
import com.modu.ModuForm.app.web.dto.user.UserDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/user")
public class UserController {
    private final UserServiceImpl userService;
    // 로그인
    @PostMapping("/login")
    public Access login(@RequestBody LoginRequestDto loginRequestDto, BindingResult bindingResult) {
        return userService.login(loginRequestDto);
    }

    // 회원가입
    @PostMapping
    public Long register(@RequestBody UserRegisterDto userRegisterDto) {
        return userService.register(userRegisterDto);
    }

    // 회원 정보 수정
    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody UserRegisterDto userRequestDto) {
        return null;
    }

    // 관리자 계정 생성
    @PostMapping("{id}/admin")
    public Long createAdmin(@PathVariable Long id, @RequestBody AdminRequestDto adminRequestDto) {
        return null;
    }

    // 회원 정보 보기
    @GetMapping("/{id}")
    public UserDetailsDto viewProfile(@PathVariable Long id) {
        return null;
    }
}

package com.zerobase.user.member.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zerobase.user.member.dto.LoginDto;
import com.zerobase.user.member.dto.SignUpRequestDto;
import com.zerobase.user.member.service.AuthService;
import com.zerobase.user.member.type.MemberPlatform;
import com.zerobase.user.member.type.MemberRole;
import com.zerobase.user.model.WebResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/api/user/sign-up")
    public WebResponse userSignUp(@RequestBody @Valid SignUpRequestDto request) {
        authService.signUp(request, MemberPlatform.ZOGIODDAE, MemberRole.ROLE_USER);
        return WebResponse.ok();
    }

    @PostMapping("/api/seller/sign-up")
    public WebResponse sellerSignUp(@RequestBody @Valid SignUpRequestDto request) {
        authService.signUp(request, MemberPlatform.ZOGIODDAE, MemberRole.ROLE_SELLER);
        return WebResponse.ok();
    }

    @GetMapping("/api/sign-up-verify")
    public WebResponse signUpVerify(@RequestParam String verifycode) {
        authService.signUpVerify(verifycode);
        return WebResponse.ok();
    }

    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDto.LoginRequestDto requestDto) {
        return null;
    }
}
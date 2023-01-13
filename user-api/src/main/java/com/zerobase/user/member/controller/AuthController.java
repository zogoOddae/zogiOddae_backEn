package com.zerobase.user.member.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zerobase.common.auth.MemberDetails;
import com.zerobase.common.type.MemberPlatform;
import com.zerobase.common.type.MemberRole;
import com.zerobase.user.exception.CustomException;
import com.zerobase.user.exception.ErrorCode;
import com.zerobase.user.member.dto.LoginRequestDto;
import com.zerobase.user.member.dto.RefreshTokenRequestDto;
import com.zerobase.user.member.dto.SignUpRequestDto;
import com.zerobase.user.member.service.AuthService;
import com.zerobase.user.model.WebResponse;
import com.zerobase.user.model.WebResponseData;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    
    @GetMapping("/auth/ping")
    public String ping() {
        return "ping";
    }

    @GetMapping("/auth/pong")
    //@Secured("ROLE_SELLER")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String pong() {
        MemberDetails memberDetails = (MemberDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return memberDetails.getEmail();
        //return "pong";
    }

    @PostMapping("/api/auth/sign-up/user")
    public WebResponse userSignUp(@RequestBody @Valid SignUpRequestDto request) {
        authService.signUp(request, MemberPlatform.ZOGIODDAE, MemberRole.ROLE_USER);
        return WebResponse.ok();
    }

    @PostMapping("/api/auth/sign-up/seller")
    public WebResponse sellerSignUp(@RequestBody @Valid SignUpRequestDto request) {
        authService.signUp(request, MemberPlatform.ZOGIODDAE, MemberRole.ROLE_SELLER);
        return WebResponse.ok();
    }

    @GetMapping("/api/auth/sign-up-verify")
    public WebResponse signUpVerify(@RequestParam String verifycode) {
        authService.signUpVerify(verifycode);
        return WebResponse.ok();
    }

    @PutMapping("/api/auth/refreshtoken")
    public WebResponseData<?> refreshToken(@RequestBody @Valid RefreshTokenRequestDto request) {
        return WebResponseData.ok(authService.refreshToken(request));
    }    

    @PostMapping("/api/auth/login")
    public WebResponseData<?> login(@RequestBody @Valid LoginRequestDto request) {
        return WebResponseData.ok(authService.login(request));
    }

    @GetMapping("/api/auth/logout")
    public WebResponse logout(@AuthenticationPrincipal MemberDetails memberDetails) {
        if(memberDetails == null) {
            throw new CustomException(ErrorCode.NOT_AUTHROIZED);
        }
        
        authService.logout(memberDetails.getId());
        return WebResponse.ok();
    }
}
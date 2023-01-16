package com.zerobase.user.member.controller;


import com.zerobase.user.member.service.AuthService;
import com.zerobase.user.member.service.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import com.zerobase.user.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final com.zerobase.user.member.service.MailService MailService;
    private final AuthService authService;

    @Value("${api.key}")
    private String API_KEY;
}

package com.zerobase.user.member.controller;


import org.springframework.web.bind.annotation.RestController;

import com.zerobase.user.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {
    
    private final MemberService memberService;
}

package com.zerobase.user.member.service;

import org.springframework.stereotype.Service;

import com.zerobase.user.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

}
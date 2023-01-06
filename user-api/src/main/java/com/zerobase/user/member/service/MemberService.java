package com.zerobase.user.member.service;

import static com.zerobase.user.exception.ErrorCode.MEMBER_NOT_FOUND;

import com.zerobase.user.exception.CustomException;
import com.zerobase.user.jwt.JwtTokenProvider;
import com.zerobase.user.member.dto.LoginDto.LoginRequestDto;
import com.zerobase.user.member.dto.SignUpDto.SignUpRequestDto;
import com.zerobase.user.member.entity.Member;
import com.zerobase.user.member.repository.MemberRepository;
import com.zerobase.user.member.type.MemberRole;
import com.zerobase.user.member.type.MemberStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {


    private final MemberRepository memberRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public Long registerMember(SignUpRequestDto requestDto) {
        Member newMember = Member.builder()
                .username(requestDto.getUsername())
                .email(requestDto.getEmail())
                .password(bCryptPasswordEncoder.encode(requestDto.getPassword()))
                .status(MemberStatus.ACTIVE)
                .roles(MemberRole.ROLE_USER)
                .build();

        memberRepository.save(newMember);

        return newMember.getId();
    }


    public String login(LoginRequestDto requestDto) {
        Member loginMember = memberRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

        return jwtTokenProvider.createToken(loginMember.getEmail(), loginMember.getRoles());
    }
}




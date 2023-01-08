package com.zerobase.user.member.service;

import static com.zerobase.user.exception.ErrorCode.MEMBER_NOT_FOUND;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zerobase.user.exception.CustomException;
import com.zerobase.user.exception.ErrorCode;
import com.zerobase.user.jwt.JwtTokenProvider;
import com.zerobase.user.member.dto.LoginDto.LoginRequestDto;
import com.zerobase.user.member.dto.SignUpRequestDto;
import com.zerobase.user.member.dto.SignUpRequestVerifyDto;
import com.zerobase.user.member.entity.Member;
import com.zerobase.user.member.repository.MemberRepository;
import com.zerobase.user.member.type.MemberPlatform;
import com.zerobase.user.member.type.MemberRole;
import com.zerobase.user.member.type.MemberStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final String REDIS_KEY_UUID = "MEMBER::VERIFIY::KEY::%s";
    private static final String REDIS_KEY_EMAIL = "MEMBER::VERIFIY::EMAIL::%s";
    private static final Integer SIGNUP_VERIFY_EXPIRE_DAYS = 7;

    private final MemberRepository memberRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;
    private final MailService mailService;

    public void signUp(SignUpRequestDto request, MemberPlatform platform, MemberRole role) {
        String emailKey = String.format(REDIS_KEY_EMAIL, request.getEmail());
        if (redisService.hasKey(emailKey)) {
            throw new CustomException(ErrorCode.SIGNUP_REQUEST_ALREADY_EXIST);
        }

        String uuid = "";
        String uuidKey = "";
        while (true) {
            uuid = UUID.randomUUID().toString();
            uuidKey = String.format(REDIS_KEY_UUID, uuid);
            if (!redisService.hasKey(uuidKey)) {
                break;
            }
        }

        redisService.putRedis(uuidKey, request.getEmail());

        SignUpRequestVerifyDto verifyDto = SignUpRequestVerifyDto.builder()
            .email(request.getEmail())
            .username(request.getUsername())
            .password(request.getPassword())
            .platform(platform)
            .role(role)
            .build();
        redisService.putRedis(emailKey, verifyDto, TimeUnit.DAYS, SIGNUP_VERIFY_EXPIRE_DAYS);

        mailService.sendSignUpVerify(request.getEmail(), request.getUsername(), uuid);
    }

    public void signUpVerify(String verifycode) {
        String uuidKey = String.format(REDIS_KEY_UUID, verifycode);        
        String email = redisService.getRedis(uuidKey, String.class);
        if(email == null) {
            throw new CustomException(ErrorCode.SIGNUP_VERIFY_REQUEST_NOT_EXIST);
        }

        String emailKey = String.format(REDIS_KEY_EMAIL, email);
        SignUpRequestVerifyDto request = redisService.getRedis(emailKey, SignUpRequestVerifyDto.class);
        if(request == null) {
            throw new CustomException(ErrorCode.SIGNUP_VERIFY_REQUEST_NOT_EXIST);
        }

        Member newMember = Member.builder()
            .platform(request.getPlatform())
            .email(request.getEmail())
            .username(request.getUsername())            
            .password(bCryptPasswordEncoder.encode(request.getPassword()))
            .status(MemberStatus.ACTIVE)
            .role(request.getRole())            
            .build();
        memberRepository.save(newMember);

        redisService.delRedis(uuidKey);
        redisService.delRedis(emailKey);        
    }

    public String login(LoginRequestDto requestDto) {
        Member loginMember = memberRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

        return jwtTokenProvider.createToken(loginMember.getEmail(), loginMember.getRole());
    }
}
package com.zerobase.user.member.service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mysql.cj.util.StringUtils;
import com.zerobase.auth.JWTTokenProvider;
import com.zerobase.type.MemberPlatform;
import com.zerobase.type.MemberRole;
import com.zerobase.type.MemberStatus;
import com.zerobase.user.exception.CustomException;
import com.zerobase.user.exception.ErrorCode;
import com.zerobase.user.member.dto.LoginRequestDto;
import com.zerobase.user.member.dto.LoginResponseDto;
import com.zerobase.user.member.dto.RefreshTokenRequestDto;
import com.zerobase.user.member.dto.RefreshTokenResponseDto;
import com.zerobase.user.member.dto.SignUpRequestDto;
import com.zerobase.user.member.dto.SignUpRequestVerifyDto;
import com.zerobase.user.member.entity.Member;
import com.zerobase.user.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final String REDIS_KEY_UUID = "MEMBER::VERIFIY::KEY::%s";
    private static final String REDIS_KEY_EMAIL = "MEMBER::VERIFIY::EMAIL::%s";
    private static final String REDIS_KEY_REFRESHTOKEN = "MEMBER::REFRESHTOKEN::%d";
    private static final Long SIGNUP_VERIFY_EXPIRE_DAYS = 7L;
    private static final Long ACCESS_TOKEN_EXPIRE_TIME = 1000L * 60L * 60L * 2L;                // 2시간
    private static final Long REFRESH_TOKEN_EXPIRE_TIME = 1000L * 60L * 60L * 24L * 60L;        // 60일

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;    
    private final RedisService redisService;
    private final MailService mailService;
    private final JWTTokenProvider tokenProvider;

    private String getMemberVerifyUUIDKey(String verifyCode) {
        return String.format(REDIS_KEY_UUID, verifyCode);
    }

    private String getMemberVerifyEmailKey(String email) {
        return String.format(REDIS_KEY_EMAIL, email);
    }

    private String getRefreshTokenKey(Long id) {
        return String.format(REDIS_KEY_REFRESHTOKEN, id);
    }

    public void signUp(SignUpRequestDto request, MemberPlatform platform, MemberRole role) {
        String emailKey = this.getMemberVerifyEmailKey(request.getEmail());
        if (redisService.hasKey(emailKey)) {
            //redisService.delRedis(emailKey);
            throw new CustomException(ErrorCode.SIGNUP_REQUEST_ALREADY_EXIST);
        }

        String uuid = "";
        String uuidKey = "";
        while (true) {
            uuid = UUID.randomUUID().toString();
            uuidKey = this.getMemberVerifyUUIDKey(uuid);
            if (!redisService.hasKey(uuidKey)) {
                break;
            }
        }

        redisService.putRedis(uuidKey, request.getEmail(), TimeUnit.DAYS, SIGNUP_VERIFY_EXPIRE_DAYS);

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
        String uuidKey = this.getMemberVerifyUUIDKey(verifycode);
        String email = redisService.getRedis(uuidKey, String.class);
        if(email == null) {
            throw new CustomException(ErrorCode.SIGNUP_VERIFY_REQUEST_NOT_EXIST);
        }

        String emailKey = this.getMemberVerifyEmailKey(email);
        SignUpRequestVerifyDto request = redisService.getRedis(emailKey, SignUpRequestVerifyDto.class);
        if(request == null) {
            throw new CustomException(ErrorCode.SIGNUP_VERIFY_REQUEST_NOT_EXIST);
        }

        Member newMember = Member.builder()
            .platform(request.getPlatform())
            .email(request.getEmail())
            .username(request.getUsername())            
            .password(passwordEncoder.encode(request.getPassword()))
            .status(MemberStatus.ACTIVE)
            .role(request.getRole())            
            .build();
        memberRepository.save(newMember);

        redisService.delRedis(uuidKey);
        redisService.delRedis(emailKey);        
    }

    public LoginResponseDto login(LoginRequestDto requestDto) {        
        Member loginMember = memberRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        if(!passwordEncoder.matches(requestDto.getPassword(), loginMember.getPassword())) {
            throw new CustomException(ErrorCode.PASSWORD_MISMATCH);
        }

        String accessToken = tokenProvider.generateToken(
            loginMember.getId(),
            loginMember.getUsername(),
            loginMember.getEmail(),
            loginMember.getRole(),
            ACCESS_TOKEN_EXPIRE_TIME );

        String refreshToken = tokenProvider.generateToken(
            loginMember.getId(),
            loginMember.getUsername(),
            loginMember.getEmail(),
            loginMember.getRole(),
            REFRESH_TOKEN_EXPIRE_TIME );

        String refreshTokenKey = this.getRefreshTokenKey(loginMember.getId());
        if(redisService.hasKey(refreshTokenKey)) {
            redisService.delRedis(refreshTokenKey);
        }
        redisService.putRedis(refreshTokenKey, refreshToken, TimeUnit.MILLISECONDS, REFRESH_TOKEN_EXPIRE_TIME);
        
        return LoginResponseDto.builder()
                .id(loginMember.getId())
                .username(loginMember.getUsername())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public RefreshTokenResponseDto refreshToken(RefreshTokenRequestDto request) {
        String refreshToken = request.getRefreshToken();
        if(StringUtils.isNullOrEmpty(refreshToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        if(tokenProvider.isExpiredToken(refreshToken)) {
            throw new CustomException(ErrorCode.EXPIRED_TOKEN);
        }

        Long userId = tokenProvider.getUserId(request.getRefreshToken());
        String refreshTokenKey = this.getRefreshTokenKey(userId);
        String savedRefreshToken = redisService.getRedis(refreshTokenKey, String.class);
        if(StringUtils.isNullOrEmpty(savedRefreshToken)) {
            throw new CustomException(ErrorCode.EXPIRED_TOKEN);
        }

        if(!savedRefreshToken.equals(refreshToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        Member member = memberRepository.findById(userId)
            .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));        
    
        String accessToken = tokenProvider.generateToken(
            member.getId(),
            member.getUsername(),
            member.getEmail(),
            member.getRole(),
            ACCESS_TOKEN_EXPIRE_TIME );

        String newRefreshToken = tokenProvider.generateToken(
            member.getId(),
            member.getUsername(),
            member.getEmail(),
            member.getRole(),
            REFRESH_TOKEN_EXPIRE_TIME );    

        redisService.delRedis(refreshTokenKey);
        redisService.putRedis(refreshTokenKey, newRefreshToken, TimeUnit.MILLISECONDS, REFRESH_TOKEN_EXPIRE_TIME);
            
        return RefreshTokenResponseDto.builder()
                .id(member.getId())
                .username(member.getUsername())
                .accessToken(accessToken)
                .refreshToken(newRefreshToken)
                .build();
    }

    public void logout(Long userId) {
        // todo token방식에서 logout에 필요한 기능은.?
        String refreshTokenKey = this.getRefreshTokenKey(userId);
        redisService.delRedis(refreshTokenKey);
    }    
}
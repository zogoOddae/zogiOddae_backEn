package com.zerobase.member.member.dto;

import lombok.Getter;

@Getter
public class KakaoLoginRequestDto {
    private String accessToken;
    private String refreshToken;    
}
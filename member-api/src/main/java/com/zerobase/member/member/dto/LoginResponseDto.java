package com.zerobase.member.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponseDto {
    private Long id;
    private String username;
    private String accessToken;
    private String refreshToken;
}
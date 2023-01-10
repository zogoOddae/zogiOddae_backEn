package com.zerobase.user.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RefreshTokenResponseDto {
    private Long id;
    private String username;
    private String accessToken;
    private String refreshToken;
}

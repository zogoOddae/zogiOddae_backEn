package com.zerobase.member.member.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class RefreshTokenRequestDto {
    @NotBlank
    @NotNull
    private String refreshToken;
}
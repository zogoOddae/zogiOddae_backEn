package com.zerobase.member.member.dto;

import com.zerobase.common.type.MemberPlatform;
import com.zerobase.common.type.MemberRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpRequestVerifyDto {
    private String email;
    private String username;
    private String password;
    private MemberRole role;
    private MemberPlatform platform;    
}

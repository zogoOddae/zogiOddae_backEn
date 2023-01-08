package com.zerobase.user.member.dto;

import com.zerobase.type.MemberPlatform;
import com.zerobase.type.MemberRole;

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

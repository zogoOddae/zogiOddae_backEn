package com.zerobase.user.member.controller;


import com.zerobase.common.type.MemberRole;
import com.zerobase.common.type.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoDto {

    private String email;
    private MemberStatus status;
    private MemberRole memberRole;

}

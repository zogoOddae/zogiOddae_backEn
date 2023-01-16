package com.zerobase.user.member.dto;


import com.zerobase.type.MemberRole;
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
    private MemberRole memberRole;

}

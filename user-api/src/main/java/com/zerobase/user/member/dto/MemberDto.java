package com.zerobase.user.member.dto;


import com.zerobase.type.MemberRole;
import com.zerobase.common.type.MemberStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String email;
    private String nickName;
    private String profile;
    private MemberStatus status;
    private MemberRole role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

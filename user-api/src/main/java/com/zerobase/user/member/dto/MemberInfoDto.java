package com.zerobase.user.member.dto;


import java.time.LocalDateTime;

import com.zerobase.common.type.MemberRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoDto {

    private Long memberId;
    private Long userId;
    private String username;
    private String nickname;
    private String password;
    private String phoneNo;
    private Long emailKey;
    private String email;
    private String memberStatus;
    private MemberRole memberRole;
    private String profileImage;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private String zipcode;
    private String address;
    private String addressDetail;



    public MemberInfoDto(Long memberId, Long userId, String email, String nickname) {
        this.memberId = memberId;
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;


    }


}

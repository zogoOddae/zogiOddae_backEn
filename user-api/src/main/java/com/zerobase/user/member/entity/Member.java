package com.zerobase.user.member.entity;

import com.zerobase.common.type.MemberStatus;
import com.zerobase.type.MemberRole;
import com.zerobase.user.member.dto.MemberDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.zerobase.common.type.MemberPlatform;

import com.zerobase.user.base.entity.BaseEntity;

import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Member extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Setter
    @Column(name = "username", nullable = false, length = 32)
    private String username;
    @Setter
    @Column(name = "nickname", nullable = false, length = 32)
    private String nickname;
    @Setter
    @Column(name = "password", nullable = false, length = 1024)
    private String password;
    @Column(name = "phoneNo")
    private String phoneNo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emailKey;
    @Setter
    @Column(name = "email", unique = true, nullable = false, length = 64)
    private String email;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Setter
    @Column(name = "profileImage")
    private String profileImage;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;

    @Setter
    @Column(name = "zipcode")
    private String zipcode;
    @Setter
    @Column(name = "address")
    private String address;
    @Setter
    @Column(name = "addressDetail")
    private String addressDetail;


    @Column(name = "platform", nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberPlatform platform;

    // kakao id. platform이 zogioddae인 경우엔 null
    @Column(name = "platformId", unique = false, nullable = true, length = 256)
    private String platformId;




    @OneToMany
    private List<Member> members = new ArrayList<>();

    @Builder
    public Member(String email, String password, String nickname, MemberStatus status,
            MemberRole role) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.status = status;
        this.role = role;
    }

    public void editNickName(String nickname) {
        this.nickname = nickname;
        this.createdAt = LocalDateTime.now();
    }

    public void resetPassword(String password) {
        this.password = password;
        this.updatedAt = LocalDateTime.now();
    }

    public void changeMemberStatus(MemberStatus status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();

        if (status == MemberStatus.DELETED) {
            this.updatedAt = LocalDateTime.now();
            this.deletedAt = LocalDateTime.now();
        }

    }

    public MemberDto toMemberDto() {
        return MemberDto.builder()
                .id(this.id)
                .email(this.email)
                .nickName(this.nickname)
                .profile(this.profileImage)
                .role(this.role)
                .status(this.status)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }

    public void editProfile(String profileImage) {
        this.profileImage = profileImage;
        this.updatedAt = LocalDateTime.now();
    }









}
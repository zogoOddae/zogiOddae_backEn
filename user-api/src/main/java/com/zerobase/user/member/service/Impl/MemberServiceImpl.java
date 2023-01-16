package com.zerobase.user.member.service.Impl;

import com.zerobase.user.member.dto.ChangeNickname;
import com.zerobase.user.member.dto.MemberInfoDto;
import com.zerobase.user.member.service.MemberService;
import java.util.List;
import org.springframework.stereotype.Service;

import com.zerobase.user.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {


    /**
     * 수정예정
     */
    private final MemberRepository memberRepository;


    @Override
    @Transactional(readOnly = true)
    public MemberInfoDto mypage() {
        return null;
    }

    @Override
    public String updateProfile(List<MultipartFile> multipartFiles) {
        return null;
    }

    @Override
    public String changeNickname(ChangeNickname changeNickname) {
        return null;
    }
}
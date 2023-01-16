package com.zerobase.user.member.service;

import com.zerobase.user.member.dto.ChangeNickname;
import com.zerobase.user.member.dto.MemberInfoDto;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface MemberService {
    MemberInfoDto mypage();
    String updateProfile(List<MultipartFile> multipartFiles);
    String changeNickname(ChangeNickname changeNickname);
}

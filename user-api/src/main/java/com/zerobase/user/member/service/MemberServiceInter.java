package com.zerobase.user.member.service;

import com.zerobase.user.member.dto.UpdateNickname;
import com.zerobase.user.member.dto.MemberInfoDto;
import com.zerobase.user.member.dto.form.MemberForm;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import  com.zerobase.user.member.dto.*;

public interface MemberServiceInter {
    MemberInfoDto mypage(String username, String email, String name, String email1);
    String updateNickname(UpdateNickname updateNickname);
    UserDetails findByUsername(String username);


    String updatePassword(UpdatePassword updatePassword);


    Optional<Object> findByMembername(String username);

    String updatePassword(String username, String encodedNewPwd);
}

package com.zerobase.user.member.service;

import com.zerobase.common.type.MemberStatus;
import com.zerobase.user.exception.AlreadyUsageNickNameException;
import com.zerobase.user.exception.InvalidPasswordFormatException;
import com.zerobase.user.exception.LoginInfoErrorException;
import com.zerobase.user.exception.NotFoundMemberException;
import com.zerobase.user.exception.UnauthorizedMemberException;
import com.zerobase.user.member.dto.MemberDto;
import com.zerobase.user.member.dto.MemberEditNickNameDto;
import com.zerobase.user.member.dto.MemberPasswordResetDto;
import com.zerobase.user.member.dto.MemberWithDrawDto;
import com.zerobase.user.member.entity.Member;
import com.zerobase.user.model.MemberResponse;
import com.zerobase.zogi_o_ddae.domain.type.MemberGrant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import lombok.Builder;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.zerobase.user.exception.*;

import com.zerobase.user.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import com.zerobase.accomodation.domain.type.*;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public abstract class MemberService implements UserDetailsService {



    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final RedisTemplate redisTemplate;
    private final MemberGrant memberGrant;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    public MemberDto getMemberInfo(String email) {
        Member findMember = checkInvalidMember(email);
        return findMember.toMemberDto();
    }


    private Member checkInvalidMember(String email) {
        Member findMember = this.memberRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundMemberException());

        if (findMember.getStatus() == MemberStatus.UNAUTHORIZED) {
            throw new UnauthorizedMemberException();
        }

        if (findMember.getStatus() == MemberStatus.DELETED) {
            throw new WithdrawMemberException();
        }

        return findMember;
    }
    // 정보 수정(nickName)
    @Transactional
    public MemberDto editMemberNickName(MemberEditNickNameDto memberEditNickNameDto) {
        Member findMember = checkInvalidMember(memberEditNickNameDto.getEmail());

        boolean existNickName = this.memberRepository.existsByNickName(
                memberEditNickNameDto.getNickName());

        if (existNickName) {
            throw new AlreadyUsageNickNameException();
        }

        findMember.editNickName(memberEditNickNameDto.getNickName());
        this.memberRepository.save(findMember);

        return findMember.toMemberDto();
    }

    @Transactional
    public boolean resetPassword(
            String uuid,
            MemberPasswordResetDto memberPasswordResetDto) {
        if (checkInvalidPassword(memberPasswordResetDto.getPassword())) {
            throw new InvalidPasswordFormatException();
        }

        Member findMember = this.memberRepository.findByUuid(uuid)
                .orElseThrow(() -> new UnauthorizedMemberException());

        String newPassword = memberPasswordResetDto.getPassword();

        findMember.resetPassword(this.passwordEncoder.encode(newPassword));

        this.memberRepository.save(findMember);

        return true;
    }


    @Transactional
    public Long updateInfo(String email, String newName) {
        Member member  = memberRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(email));

        member.setUsername(newName);
        member.setEmail(email);
        return member.getId();
    }


    @Transactional
    public MemberDto withdrawMember(MemberWithDrawDto memberWithDrawDto) {
        Member findMember = checkInvalidMember(memberWithDrawDto.getEmail());

        if (!checkCorrectPassword(memberWithDrawDto.getPassword(), findMember.getPassword())) {
            throw new LoginInfoErrorException();
        }

        findMember.changeMemberStatus(MemberStatus.DELETED);
        return this.memberRepository.save(findMember).toMemberDto();
    }

    private boolean checkInvalidEmail(String email) {
        return !Pattern.matches("^[a-zA-Z.].+[@][a-zA-Z].+[.][a-zA-Z]{2,4}$",email);
    }

    private boolean checkInvalidPassword(String password) {
        return Pattern.matches("^[^0-9a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣~!@#$%^&*()-_=+,.?]{8,}$",password);
    }
    private boolean checkCorrectPassword(String inputPassword,String password) {
        return BCrypt.checkpw(inputPassword,password);
    }


}
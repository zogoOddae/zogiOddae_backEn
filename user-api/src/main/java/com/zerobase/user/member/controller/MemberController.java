package com.zerobase.user.member.controller;


import com.zerobase.common.auth.MemberDetails;
import com.zerobase.user.exception.CustomException;
import com.zerobase.user.exception.ErrorCode;
import com.zerobase.user.member.dto.MemberEditNickNameDto;
import com.zerobase.user.member.service.AuthService;
import com.zerobase.user.member.service.MailService;
import com.zerobase.user.member.service.MemberService;
import lombok.var;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.zerobase.user.member.dto.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class MemberController {

   // @Autowired MemberRepository memberRepository;
    private final MailService mailService;
    private final AuthService authService;
    private final MemberService memberService;

    //회원가입 페이지로 이동
    @GetMapping(value = "/new")
    public String memberForm(Model model) {
        model.addAttribute("memberDto", new MemberDto());

        return "/memberForm";
    }

    //로그인 페이지로 이동
    @GetMapping(value = "/login")
    public String loginMember(){
        return "/memberLoginForm";
    }



   //회원 삭제
    @PostMapping("/delete")
    public ResponseEntity<?> withdrawMember(@RequestBody MemberWithDrawDto memberWithDrawDto) {
        return ResponseEntity.ok(this.memberService.withdrawMember(memberWithDrawDto));
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_SELLER')")
    public ResponseEntity<?> memberInfo(@RequestParam String email) {
        var result = this.memberService.getMemberInfo(email);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/edit/nickname")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_SELLER')")
    public ResponseEntity<?> memberEditNickName(
            @RequestBody MemberEditNickNameDto memberEditNickNameDto) {
        var result = this.memberService.editMemberNickName(memberEditNickNameDto);
        return ResponseEntity.ok(result);
    }


















    /*
    @PutMapping("/api/users/edit/profile")
    @PreAuthorize("hasRole('MEMBER') or hasRole('MERCHANT')")
    public ResponseEntity<?> memberEditProfile(
            @RequestBody MemberEditProfileDto memberEditProfileDto) {
        var result = this.memberService.editMemberProfile(memberEditProfileDto);
        return ResponseEntity.ok(result);
    }
     */

//    @PostMapping("/api/auth/password")
//    public ResponseEntity<?> sendPasswordResetEmail(@RequestBody MemberInfoDto memberInfoDto) {
//        return ResponseEntity.ok(memberInfoDto);
//    }
//
//    @PutMapping("/api/auth/resetPassword")
//    public ResponseEntity<?> passwordReset(
//            @RequestParam String uuid,
//            @RequestBody MemberPasswordResetDto memberPasswordResetDto)
//    {
//        return ResponseEntity.ok(memberService.resetPassword(uuid,memberPasswordResetDto));
//    }




}


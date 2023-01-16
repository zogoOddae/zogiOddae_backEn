package com.zerobase.user.member.controller;


import com.zerobase.user.member.entity.Member;
import com.zerobase.user.member.repository.MemberRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyPageController {

    private final MemberRepository memberRepository;

    public MyPageController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/mypage/me")
    public String myPageHome(Model model, @AuthenticationPrincipal Member currentMember) {

        MemberForm memberForm = new MemberForm();
        memberForm.setName(currentMember.getUsername());
        memberForm.setEmail(currentMember.getEmail());

        model.addAttribute("memberForm", memberForm);
        return "mypage/me";


    }

    @PostMapping("/mypage/me")
    public String userEdit(MemberForm form, BindingResult result, @AuthenticationPrincipal Member currentMember) {
        if(result.hasErrors()) {
            return "redirect:/mypage/me";
        }

        memberRepository.updateInfo(currentMember.getUsername(), form.getName(), form.getEmail());
        currentMember.setUsername(form.getName());
        currentMember.setEmail(form.getEmail());

        return "redirect:/mypage/me";
    }




}


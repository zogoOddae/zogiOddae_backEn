package com.zerobase.user.member.controller;

import com.zerobase.user.member.dto.UpdatePassword;
import com.zerobase.user.member.dto.form.MemberForm;
import com.zerobase.user.member.entity.Category;
import com.zerobase.user.member.entity.Member;
import com.zerobase.user.member.entity.Notice;
import com.zerobase.user.member.service.CategoryService;
import com.zerobase.user.member.service.CommentService;
import com.zerobase.user.member.service.MemberServiceInter;
import com.zerobase.user.member.service.NoticeService;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import org.hibernate.annotations.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class MyPageController {

    private final NoticeService noticeService;
    private final MemberServiceInter memberService;
    private final CategoryService categoryService;
    private CommentService commentService;

    public MyPageController(NoticeService noticeService, MemberServiceInter memberService,
            CategoryService categoryService) {
        this.noticeService = noticeService;
        this.memberService = memberService;
        this.categoryService = categoryService;
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

        memberService.mypage(currentMember.getUsername(),currentMember.getEmail(), form.getName(), form.getEmail());
        currentMember.setUsername(form.getName());
        currentMember.setEmail(form.getEmail());

        return "redirect:/mypage/me";
    }



    @GetMapping("/mypage/comments")
    public String myComments(Model model, @AuthenticationPrincipal Member currentMember,
            @PageableDefault Pageable pageable) {
        Member member = (Member) memberService.findByMembername(currentMember.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException(currentMember.getUsername()));
        Optional<Object> comments = commentService.findListByMember(member, pageable);

        List<Notice> categoryList = categoryService.findAll();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("comments", comments);
        return "mypage/comments";
    }
    @PostMapping("/mypage/password")
    public String passwordEdit(Model model,
            UpdatePassword form,
            BindingResult result,
            @AuthenticationPrincipal Member currentMember) {
        if (result.hasErrors()) {
            return "redirect:/mypage/password";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!encoder.matches(form.getPassword(), currentMember.getPassword())) {
            model.addAttribute("error", "현재 패스워드 불일치");
            return "mypage/passwordError";
        }

        if(form.getNewPassword().equals(form.getPassword())){
            model.addAttribute("error", "동일한 패스워드");
            return "mypage/passwordError";
        }

        if (!form.getNewPassword().equals(form.getNewPasswordConfirm())) {
            model.addAttribute("error", "새 패스워드 불일치");
            return "mypage/passwordError";
        }

        String encodedNewPwd = encoder.encode(form.getNewPassword());
        memberService.updatePassword(currentMember.getUsername(), encodedNewPwd);
        currentMember.setPassword(encodedNewPwd);
        return "redirect:/mypage/me";
    }

    /*
    @GetMapping("/mypage/contents")
    public String myContents(Model model, @AuthenticationPrincipal Member currentMember,
            @PageableDefault Pageable pageable) {
        Member member = memberService.findByUsername(currentMember.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException(currentMember.getUsername()));

        Notice notices = NoticeService.getPostListByMember(member, pageable);

        List<Notice> categoryList = categoryService.findAll();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("notices", notices);
        return "mypage/contents";
    }

     */

}

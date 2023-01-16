package com.zerobase.user.member.controller;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {

    /**
     * 마이페이지에서 정보 변경시, 사용
     */

    @NotEmpty(message = "닉네임은 필수입니다.")
    private String name;
    @NotEmpty(message = "이메일은 필수입니다.")
    private String email;

}

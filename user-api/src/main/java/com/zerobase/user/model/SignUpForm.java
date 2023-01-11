package com.zerobase.user.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SignUpForm {

    @Email(message = "이메일 형식에 맞게 입력해 주세요.")
    @NotBlank(message = "아이디는 필수 항목 합니다.")
    private String email;
    @NotBlank(message = "이름은 필수 항목 합니다.")
    private String name;
    @NotBlank(message = "비밀번호는 필수 항목 합니다.")
    private String password;
    @NotBlank(message = "연락처는 필수 항목 합니다.")
    private String phoneNumber;

}

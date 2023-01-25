package com.zerobase.member.member.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    @Email
    @NotBlank
    @NotNull
    private String email;

    @NotBlank
    @NotNull
    @Size(min = 8, max = 20)
    // @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+).{8,20}")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[~$@$!%*#?&_])[A-Za-z0-9~$@$!%*#?&_]{8,20}$")
    private String password;
}
package com.zerobase.user.member.dto;

import com.zerobase.user.member.entity.Member;
import javax.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


@Data
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdatePassword {

    private String password;

    @Length(min = 8, max = 50)
    @NotEmpty(message = "새로운 비밀번호를 입력해주세요.")
    private String newPassword;

    @Length(min = 8, max = 50)
    @NotEmpty(message = "비밀번호를 재입력해주세요.")
    private String newPasswordConfirm;

}

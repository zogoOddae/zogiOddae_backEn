package com.zerobase.notice.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EditAdminNoticeRequest {

    @NotNull
    private Long noticeId;

    @NotBlank
    private String subject;

    @NotBlank
    private String description;

}

package com.zerobase.notice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveNoticeRequest {

    @NotNull
    private Long noticeId;

    @NotNull
    private Long memberId;


    @NotBlank
    private String description;
}

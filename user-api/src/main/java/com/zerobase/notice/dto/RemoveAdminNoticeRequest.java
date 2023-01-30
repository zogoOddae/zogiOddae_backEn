package com.zerobase.notice.dto;


import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RemoveAdminNoticeRequest {
    @NotNull
    private Long noticeId;
}

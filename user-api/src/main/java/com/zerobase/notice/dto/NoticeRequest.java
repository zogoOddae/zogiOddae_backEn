package com.zerobase.notice.dto;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeRequest {
    private Long noticeId;

    private Long memberId;


    private Long parentId;

    private String description;
}

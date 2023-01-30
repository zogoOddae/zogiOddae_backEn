package com.zerobase.notice.exception;


import com.zerobase.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NoticeErrorCode implements ErrorCode {
    NO_UNIQUE_NOTICE("이미 작성한 공지가 존재합니다."),
    NO_AUTH_NOTICE( "관리자만 작성할 수 있습니다.");

    private final String description;


}

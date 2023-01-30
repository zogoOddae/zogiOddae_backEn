package com.zerobase.notice.exception;

import com.zerobase.user.exception.ErrorCode;

public class NoUniqueNoticeException extends NoticeException {

    public NoUniqueNoticeException() {
        super(NoticeErrorCode.NO_UNIQUE_NOTICE);
    }
}

package com.zerobase.notice.exception;

public class NoAuthNoticeException extends NoticeException {



    public NoAuthNoticeException() {
        super(NoticeErrorCode.NO_AUTH_NOTICE);
    }
}
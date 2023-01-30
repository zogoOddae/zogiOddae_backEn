package com.zerobase.notice.exception;


import com.zerobase.user.exception.ErrorCode;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
public class NoticeException extends ServiceException {

    private final NoticeErrorCode noticeErrorCode;

    protected NoticeException(NoticeErrorCode noticeErrorCode) {
        super(noticeErrorCode);
        this.noticeErrorCode = noticeErrorCode;
    }

}

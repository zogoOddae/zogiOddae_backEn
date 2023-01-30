package com.zerobase.user.exception;


import com.zerobase.notice.exception.NoticeException;
import com.zerobase.user.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(RuntimeException.class)
    public WebResponseData<Object> globalExceptionHandler(Exception e) {
        log.error("서버 내부적 오류가 발생했습니다. -> "+e);
        return WebResponseData.error(ErrorCode.SERVER_INTERVAL_ERROR);
    }


}

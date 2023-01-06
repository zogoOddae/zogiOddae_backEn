package com.zerobase.leisure.exception;

import com.zerobase.leisure.domain.model.WebResponseData;
import com.zerobase.leisure.domain.type.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(LeisureException.class)
	public WebResponseData<Object> leisureExceptionHandler(LeisureException e) {
		log.error(e.getErrorCode() + "에러가 발생했습니다.");
		return WebResponseData.error(e.getErrorCode());
	}

	@ExceptionHandler(Exception.class)
	public WebResponseData<Object> globalExceptionHandler(Exception e) {
		log.error("서버 내부적 오류가 발생했습니다. -> "+e);
		return WebResponseData.error(ErrorCode.SERVER_INTERVAL_ERROR);
	}
}

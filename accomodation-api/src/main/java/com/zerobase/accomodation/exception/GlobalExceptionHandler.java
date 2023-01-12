package com.zerobase.accomodation.exception;

import com.zerobase.accomodation.domain.model.WebResponseData;
import com.zerobase.accomodation.domain.type.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AccomodationException.class)
	public WebResponseData<Object> leisureExceptionHandler(AccomodationException e) {
		log.error(e.getErrorCode() + "에러가 발생했습니다.");
		return WebResponseData.error(e.getErrorCode());
	}

	@ExceptionHandler(RuntimeException.class)
	public WebResponseData<Object> globalExceptionHandler(Exception e) {
		log.error("서버 내부적 오류가 발생했습니다. -> "+e);
		return WebResponseData.error(ErrorCode.SERVER_INTERVAL_ERROR);
	}
}

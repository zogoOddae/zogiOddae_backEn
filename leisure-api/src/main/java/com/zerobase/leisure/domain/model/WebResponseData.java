package com.zerobase.leisure.domain.model;

import com.zerobase.leisure.domain.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class WebResponseData<T> {
	private ErrorCode code;
	private String description;
	private T data;

	public static <T> WebResponseData<T> ok(T data) {
		return new WebResponseData<T>(ErrorCode.SAMPLE_SUCCESS_CODE, ErrorCode.SAMPLE_SUCCESS_CODE.getDescription(), data);
	}

	public static <T> WebResponseData<T> error(ErrorCode errorCode, T data) {
		return new WebResponseData<T>(errorCode, errorCode.getDescription(), data);
	}
}

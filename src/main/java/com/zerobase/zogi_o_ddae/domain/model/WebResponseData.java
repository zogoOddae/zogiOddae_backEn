package com.zerobase.zogi_o_ddae.domain.model;

import com.zerobase.zogi_o_ddae.domain.type.ErrorCode;
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
	private T data;

	public static <T> WebResponseData<T> ok(T data) {
		return new WebResponseData<T>(ErrorCode.SAMPLE_SUCCESS_CODE, data);
	}

	public static <T> WebResponseData<T> error(ErrorCode errorCode, T data) {
		return new WebResponseData<T>(errorCode, data);
	}
}

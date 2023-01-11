package com.zerobase.accomodation.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
	SUCCESS("성공"),
	SAMPLE_ERROR_CODE("샘플 에러 코드입니다.");

	private final String description;
}

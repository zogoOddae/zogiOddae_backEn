package com.zerobase.leisure.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
	SAMPLE_ERROR_CODE("샘플 에러 코드입니다."),
	SAMPLE_SUCCESS_CODE("샘플 성공 코드입니다."),


	SERVER_INTERVAL_ERROR("서버 내부적 오류입니다."),
	NOT_HAD_REVIEW("등록된 리뷰가 없습니다."),
	REVIEW_NOT_FOUND("리뷰를 찾을 수 없습니다.")

	;


	private final String description;
}

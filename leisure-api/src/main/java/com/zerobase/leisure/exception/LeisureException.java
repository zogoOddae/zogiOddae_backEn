package com.zerobase.leisure.exception;

import com.zerobase.leisure.domain.type.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LeisureException extends RuntimeException{
	private ErrorCode errorCode;
	private String description;

	public LeisureException(ErrorCode errorCode) {
		this.errorCode = errorCode;
		this.description = errorCode.getDescription();
	}
}

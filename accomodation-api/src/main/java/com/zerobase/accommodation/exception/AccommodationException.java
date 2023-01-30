package com.zerobase.accommodation.exception;

import com.zerobase.accommodation.domain.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccommodationException extends RuntimeException {

    private ErrorCode errorCode;
    private String errorMessage;

    public AccommodationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}

package com.zerobase.accomodation.exception;

import com.zerobase.accomodation.domain.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccomodationException extends RuntimeException {

    private ErrorCode errorCode;
    private String errorMessage;

    public AccomodationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}

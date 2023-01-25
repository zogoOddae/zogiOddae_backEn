package com.zerobase.user.exception;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomException extends RuntimeException {

    private ErrorCode errorCode;
    private String errorMessage;
    private LocalDateTime timestamp;


    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
        this.timestamp = LocalDateTime.now();
    }

}

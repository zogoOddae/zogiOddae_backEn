package com.zerobase.user.exception;

public class InvalidPasswordFormatException extends CustomException {
    public InvalidPasswordFormatException() {
        super(ErrorCode.INVALID_PASSWORD_FORMAT);
    }
}

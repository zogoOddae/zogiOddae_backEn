package com.zerobase.user.exception;

public class AlreadyUsageNickNameException extends CustomException {
    public AlreadyUsageNickNameException() {
        super(ErrorCode.USER_EXISTS_NICKNAME);
    }
}

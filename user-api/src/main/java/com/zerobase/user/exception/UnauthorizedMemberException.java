package com.zerobase.user.exception;

public class UnauthorizedMemberException extends CustomException {
    public UnauthorizedMemberException() {
        super(ErrorCode.UNAUTHORIZED_MEMBER);
    }
}

package com.zerobase.user.exception;

public class NotFoundMemberException extends CustomException {

    public NotFoundMemberException() {
        super(ErrorCode.MEMBER_NOT_FOUND);
    }
}

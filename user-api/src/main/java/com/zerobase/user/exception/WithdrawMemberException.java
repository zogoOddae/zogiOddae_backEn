package com.zerobase.user.exception;

public class WithdrawMemberException extends CustomException {

    public WithdrawMemberException() {
        super(ErrorCode.WITHDRAW_MEMBER);
    }
}

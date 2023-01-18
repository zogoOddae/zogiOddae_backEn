package com.zerobase.user.exception;

public class LoginInfoErrorException extends CustomException {

    public LoginInfoErrorException() {
        super(ErrorCode.LOGIN_INFO_ERROR);
    }

}

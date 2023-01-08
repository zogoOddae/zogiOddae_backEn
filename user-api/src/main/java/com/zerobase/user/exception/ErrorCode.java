package com.zerobase.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // success
    SUCCESS("성공"),

    // member
    MEMBER_NOT_FOUND("사용자가 없습니다."),

    // redis
    REDIS_PUT_EMPTY_KEY("빈 Redis키 입니다."),
    REDIS_PUT_FAIL("Redis 입력 실패."),

    // Mail
    MAIL_SEND_FAIL("Mail 전송 실패."),
    CANNOT_FIND_MAIL_TEMPLATE("Mail 템플릿을 찾을 수 없습니다."),

    // SignUp
    SIGNUP_REQUEST_ALREADY_EXIST("이미 회원가입 신청이 진행중인 상태입니다."),
    SIGNUP_VERIFY_REQUEST_NOT_EXIST("회원가입 신청 정보가 존재하지 않습니다."),
    
    ;
    private final String description;
}

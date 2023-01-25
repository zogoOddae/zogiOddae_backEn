package com.zerobase.user.exception;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // success
    SUCCESS("성공",LocalDateTime.now()),

    // member
    MEMBER_NOT_FOUND("사용자가 없습니다.",LocalDateTime.now()),
    USER_EXISTS_NICKNAME( "이미 등록된 닉네임입니다.",LocalDateTime.now()),
    UNAUTHORIZED_MEMBER("허가되지 않은 사용자의 요청입니다.", LocalDateTime.now()),
    LOGIN_INFO_ERROR("로그인 정보가 올바르지 않습니다.",LocalDateTime.now()),
    // redis
    REDIS_PUT_EMPTY_KEY("빈 Redis키 입니다.",LocalDateTime.now()),
    REDIS_PUT_FAIL("Redis 입력 실패.",LocalDateTime.now()),

    // Mail
    MAIL_SEND_FAIL("Mail 전송 실패.",LocalDateTime.now()),
    CANNOT_FIND_MAIL_TEMPLATE("Mail 템플릿을 찾을 수 없습니다.",LocalDateTime.now()),

    // SignUp
    SIGNUP_REQUEST_ALREADY_EXIST("이미 회원가입 신청이 진행중인 상태입니다.",LocalDateTime.now()),
    SIGNUP_VERIFY_REQUEST_NOT_EXIST("회원가입 신청 정보가 존재하지 않습니다.",LocalDateTime.now()),

    // Login
    INVALID_PASSWORD_FORMAT("잘못된 비밀번호 형식입니다.",LocalDateTime.now()),
    PASSWORD_MISMATCH("비밀번호가 일치하지 않습니다.",LocalDateTime.now()),
    DUPLICATE_MEMBER_ID("이미 중복된 아이디가 존재합니다.",LocalDateTime.now()),
    // Token
    EXPIRED_TOKEN("기간이 만료된 토큰입니다.",LocalDateTime.now()),
    INVALID_TOKEN("토큰 정보가 올바르지 않습니다.",LocalDateTime.now()),
    WITHDRAW_MEMBER("탈퇴한 사용자 입니다.", LocalDateTime.now()),


    NOT_AUTHROIZED("인증 되지 않은 상태입니다.", LocalDateTime.now()),

    ;






    private final String description;
    private final LocalDateTime getTimeStamp;
}

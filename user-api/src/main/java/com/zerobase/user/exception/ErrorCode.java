package com.zerobase.user.exception;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
//@NoArgsConstructor
public enum ErrorCode {
    // success
    SUCCESS("성공"),

    // member
    MEMBER_NOT_FOUND("사용자가 없습니다."),
    USER_EXISTS_NICKNAME( "이미 등록된 닉네임입니다."),
    UNAUTHORIZED_MEMBER("허가되지 않은 사용자의 요청입니다."),
    LOGIN_INFO_ERROR("로그인 정보가 올바르지 않습니다."),
    // redis
    REDIS_PUT_EMPTY_KEY("빈 Redis키 입니다."),
    REDIS_PUT_FAIL("Redis 입력 실패."),

    // Mail
    MAIL_SEND_FAIL("Mail 전송 실패."),
    CANNOT_FIND_MAIL_TEMPLATE("Mail 템플릿을 찾을 수 없습니다."),

    // SignUp
    SIGNUP_REQUEST_ALREADY_EXIST("이미 회원가입 신청이 진행중인 상태입니다."),
    SIGNUP_VERIFY_REQUEST_NOT_EXIST("회원가입 신청 정보가 존재하지 않습니다."),

    // Login
    INVALID_PASSWORD_FORMAT("잘못된 비밀번호 형식입니다."),
    PASSWORD_MISMATCH("비밀번호가 일치하지 않습니다."),
    DUPLICATE_MEMBER_ID("이미 중복된 아이디가 존재합니다."),
    // Token
    EXPIRED_TOKEN("기간이 만료된 토큰입니다."),
    INVALID_TOKEN("토큰 정보가 올바르지 않습니다."),
    WITHDRAW_MEMBER("탈퇴한 사용자 입니다."),


    NOT_AUTHROIZED("인증 되지 않은 상태입니다."),

    //notice
    ALREADY_DELETED_NOTICE("이미 삭제된 공지입니다."),
    ALREADY_REGISTERED_NOTICE("이미 등록된 공지가 있습니다."),
    NOT_HAD_NOTICE("등록된 공지가 없습니다."),
    NOTICE_NOT_FOUND("공지를 찾을 수 없습니다."),

    SERVER_INTERVAL_ERROR("서버 내부적 오류입니다.");


    ;






    private final String description;



}

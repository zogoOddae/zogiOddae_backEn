package com.zerobase.member.model;

import com.zerobase.member.exception.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class WebResponse {
    private ErrorCode errorCode;

    public static WebResponse ok() {
        return new WebResponse(ErrorCode.SUCCESS);
    }

    public static WebResponse error(ErrorCode errorCode) {
        return new WebResponse(errorCode);
    }
}

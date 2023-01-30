package com.zerobase.type;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MemberPlatform {
    ZOGIODDAE,
    KAKAO
    
    ;
    @JsonCreator
    public static MemberPlatform from(String s) {
        return MemberPlatform.valueOf(s.toUpperCase());
    }

}
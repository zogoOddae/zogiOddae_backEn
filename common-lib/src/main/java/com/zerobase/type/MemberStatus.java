package com.zerobase.type;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MemberStatus {

    ACTIVE, DORMANT, INACTIVE, DELETED;


    @JsonCreator
    public static MemberStatus from(String s) {
        return MemberStatus.valueOf(s.toUpperCase());
    }
}

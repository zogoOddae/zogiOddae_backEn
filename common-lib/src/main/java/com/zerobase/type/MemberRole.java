package com.zerobase.type;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MemberRole {

    ROLE_USER, ROLE_SELLER, ROLE_ADMIN;
    @JsonCreator
    public static MemberRole from(String s) {
        return MemberRole.valueOf(s.toUpperCase());
    }
}

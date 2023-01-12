package com.zerobase.user.model;

import java.util.Map;

import com.zerobase.common.type.MemberPlatform;

public interface OAuth2UserInfo {
    MemberPlatform getPlatform();
    String getPlatformId();    
    String getEmail();
    String getName();
}
package com.zerobase.user.model;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class KakaoUserInfo {
    @Getter
    public static class Properties {
        private String nickname;
    }

    @Getter
    public static class Profile {
        private String nickname;
    }

    @Getter
    public static class  KakaoAccount {
        private boolean profile_nickname_needs_agreement;
        private Profile profile;
        private boolean has_email;
        private boolean email_needs_agreement;
        private boolean is_email_valid;
        private boolean is_email_verified;
        private String email;
    }

    private Long id;
    private LocalDateTime connected_at;
    private Properties properties;
    private KakaoAccount kakao_account;    
}
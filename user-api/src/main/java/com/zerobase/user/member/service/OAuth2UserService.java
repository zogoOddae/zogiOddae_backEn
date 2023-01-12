package com.zerobase.user.member.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.zerobase.common.auth.MemberDetails;
import com.zerobase.common.type.MemberPlatform;
import com.zerobase.common.type.MemberRole;
import com.zerobase.common.type.MemberStatus;
import com.zerobase.user.member.entity.Member;
import com.zerobase.user.member.repository.MemberRepository;
import com.zerobase.user.model.KakaoUserInfo;
import com.zerobase.user.model.OAuth2UserInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;    

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        
        OAuth2UserInfo oAuth2UserInfo = null;
        String provider = userRequest.getClientRegistration().getRegistrationId();    
        
        if(provider.equals("kakao")){	//추가
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        }
        
        MemberPlatform platform = oAuth2UserInfo.getPlatform();
        String platformId = oAuth2UserInfo.getPlatformId();	
        String username = oAuth2UserInfo.getName();
        String email = oAuth2UserInfo.getEmail();
        MemberRole role = MemberRole.ROLE_USER;

        Member member = null;
        Optional<Member> optionalMember = memberRepository.findByPlatformAndPlatformId(platform, platformId);
        if(!optionalMember.isPresent()) {
            member = Member.builder()
                .platform(platform)
                .platformId(platformId)
                .email(email)
                .username(username)
                .password("")
                .status(MemberStatus.ACTIVE)
                .role(role)
                .build();
            memberRepository.save(member);
        } else {
            member = optionalMember.get();
        }

        return MemberDetails.builder()
                .id(member.getId())
                .email(member.getEmail())
                .username(member.getUsername())
                .role(member.getRole())
                .build();
    }
}
package com.zerobase.user.member.component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.zerobase.common.auth.JWTTokenProvider;
import com.zerobase.common.auth.MemberDetails;
import com.zerobase.user.member.dto.LoginResponseDto;
import com.zerobase.user.member.service.AuthService;
import com.zerobase.user.member.service.RedisService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final AuthService authService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        MemberDetails memberDetails = (MemberDetails)authentication.getPrincipal();
        authService.login(memberDetails);




        System.out.println(request.getParameter("test"));

        getRedirectStrategy().sendRedirect(request, response, "http://localhost");
    }
}
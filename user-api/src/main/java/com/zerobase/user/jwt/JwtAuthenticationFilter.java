package com.zerobase.user.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mysql.cj.util.StringUtils;
import com.zerobase.auth.JWTTokenProvider;
import com.zerobase.type.MemberRole;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    private final JWTTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader(TOKEN_HEADER);
        if (!StringUtils.isNullOrEmpty(header) && header.startsWith(TOKEN_PREFIX)) {
            String token = header.substring(TOKEN_PREFIX.length());
            if (!StringUtils.isNullOrEmpty(token) && !tokenProvider.isExpiredToken(token)) {
                MemberDetails memberDetails = MemberDetails.builder()
                                                        .id(tokenProvider.getUserId(token))
                                                        .email(tokenProvider.getEmail(token))
                                                        .username(tokenProvider.getUsername(token))
                                                        .role(tokenProvider.getRole(token))
                                                        .build();

                SecurityContextHolder.getContext().setAuthentication(
                                    new UsernamePasswordAuthenticationToken(memberDetails, "", memberDetails.getAuthorities()));
            }
        }

        // MemberDetails memberDetails = MemberDetails.builder()
        //     .id(1l)
        //     .email("psjcap@gmail.com")
        //     .username("박성준")
        //     .role(MemberRole.ROLE_USER)
        //     .build();

        // SecurityContextHolder.getContext().setAuthentication(
        //     new UsernamePasswordAuthenticationToken(memberDetails, "", memberDetails.getAuthorities()));

        filterChain.doFilter(request, response);
    }
}
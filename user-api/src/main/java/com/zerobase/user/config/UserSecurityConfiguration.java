package com.zerobase.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.zerobase.common.auth.JwtAuthenticationFilter;
import com.zerobase.user.member.component.OAuth2SuccessHandler;
import com.zerobase.user.member.service.OAuth2UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class UserSecurityConfiguration {

    private final JwtAuthenticationFilter authendicationFilter;
    private final OAuth2UserService oauth2UserService;
    //private final OAuth2SuccessHandler successHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        // RestController
        http.httpBasic().disable();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);        

        // 로그인 필요없는 항목 설정.
        //http.authorizeRequests().antMatchers("/auth/ping").permitAll();
        //http.authorizeRequests().antMatchers("/auth/pong").permitAll();
        //http.authorizeRequests().antMatchers("/api/auth/sign-up/**").permitAll();
        //http.authorizeRequests().antMatchers("/api/auth/sign-up-verify").permitAll();
        //http.authorizeRequests().antMatchers("/api/auth/login").permitAll();

        // 로그인을 하지 않으면 "403 fobiden" 에러
        //http.authorizeRequests().anyRequest().authenticated();        
        
        http.addFilterBefore(authendicationFilter, UsernamePasswordAuthenticationFilter.class);

        // OAuth2
        http
            .oauth2Login()
                //.oauth2Login().loginPage("/token/expired")
                //.successHandler(successHandler)
                .userInfoEndpoint().userService(oauth2UserService);

        return http.build();
    }
}
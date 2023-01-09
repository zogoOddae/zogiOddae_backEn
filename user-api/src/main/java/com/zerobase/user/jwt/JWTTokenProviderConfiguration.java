package com.zerobase.user.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zerobase.auth.JWTTokenProvider;

@Configuration
public class JWTTokenProviderConfiguration {
    
    @Bean
    public JWTTokenProvider getJWTTokenProvider() {
        return new JWTTokenProvider();
    }
}

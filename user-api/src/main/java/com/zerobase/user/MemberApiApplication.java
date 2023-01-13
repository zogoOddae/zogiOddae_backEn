package com.zerobase.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(
    basePackages = {
        "com.zerobase.user",
        "com.zerobase.common"
        },

        // user-api에서는 OAuth2기능 때문에 SecurityConfig를 따로 구현.
        excludeFilters = {
            @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = com.zerobase.common.config.SecurityConfiguration.class)
        }
)
public class MemberApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberApiApplication.class, args);
    }
}

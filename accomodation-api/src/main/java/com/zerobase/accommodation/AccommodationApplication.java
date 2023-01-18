package com.zerobase.accommodation;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = { "com.zerobase.accommodation", "com.zerobase.common" })
public class AccommodationApplication {
	public static void main(String[] args) {
		SpringApplication.run(AccommodationApplication.class,args);
	}
}

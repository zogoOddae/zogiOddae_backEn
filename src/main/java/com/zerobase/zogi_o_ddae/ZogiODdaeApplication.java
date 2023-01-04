package com.zerobase.zogi_o_ddae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ZogiODdaeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZogiODdaeApplication.class, args);
	}

}

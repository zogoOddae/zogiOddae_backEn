package com.zerobase.member.swagger;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket restAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
        	.useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zerobase.member"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Member API")
                .version("1.0.0")
                .description("Member-API Document")
                .build();
    }


    /*
	private ApiInfo swaggerInfo() {
		return new ApiInfoBuilder().title("Member API")
			.description("Member API Swagger").build();
	}

	@Bean
	public Docket swaggerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
			.consumes(getConsumeContentTypes())
			.produces(getProduceContentTypes())
			.apiInfo(swaggerInfo()).select()
			.apis(RequestHandlerSelectors.basePackage("com.zerobase.member.member.controller"))
			.paths(PathSelectors.any())
			.build()
			.useDefaultResponseMessages(false);
	}

	private Set<String> getConsumeContentTypes() {
		Set<String> consumes = new HashSet<>();
		consumes.add("application/json;charset=UTF-8");
		consumes.add("application/x-www-form-urlencoded");
		return consumes;
	}

	private Set<String> getProduceContentTypes() {
		Set<String> produces = new HashSet<>();
		produces.add("application/json;charset=UTF-8");
		return produces;
	}
    */
}
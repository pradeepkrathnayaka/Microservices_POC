package com.cg.fm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Slf4j
public class SwaggerConfig{
	
	public static final String TAG_1 = "Friend Management API";
	
	@Bean
    public Docket builRestApi(){
		log.info("Building API");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .tags(new Tag(TAG_1, "Rest API to manage friends."))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())               
               // .paths(PathSelectors.ant("/friends/**"))               
                .build();
    }
	
	private ApiInfo apiInfo() {
	    return new ApiInfoBuilder()
	        .title("﻿SPS Friend Management.")
	        .description("SPS Friend Management.")
	        .termsOfServiceUrl("https://www.capgemini.com/")
	        .version("1.0.0")
	        .build();
	  }
	
}

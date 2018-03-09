package com.practice.rest.webservice.restfulwebservice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	public static final Set<String> PRODUCES_CONSUMES =new HashSet<String>(Arrays.asList("application/json","application/xml"));
	public static final Contact contact= new Contact("Lakshman Muriyal", "https://ciber.com", "lakshman.muriyal@gmail.com");
	public static final ApiInfo API_INFO= new ApiInfo("Rest API title", "Rest API documentation", "1.0", "urn:tos", contact, "Apache 2.0", " apache url comes here");
	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
				.produces(PRODUCES_CONSUMES)
				.consumes(PRODUCES_CONSUMES)
				.apiInfo(API_INFO);
	}

}

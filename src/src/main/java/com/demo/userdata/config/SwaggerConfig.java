package com.demo.userdata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.enable(true)
				.apiInfo(new ApiInfoBuilder()
						.title("User Signup API Swagger")
						.description("Swagger Description details")
						.version("1.0").build())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.demo.userdata.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("User Signup API")
				.description("User Signup API")
				.termsOfServiceUrl("http://user-signup.com")
				.contact(new Contact("Vidya Init", "Vidya Inti", "vidyainti@gmail.com"))
				.license("Vidya Inti")
				.licenseUrl("vidyainti@gmail.com")
				.version("1.0")
				.build();
	}

}
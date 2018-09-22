package com.challenge.productreview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@SpringBootApplication
public class ProductReviewServiceApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ProductReviewServiceApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.challenge.productreview.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}


	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Product Review",
				"Challenge Code by Javier Benavides",
				"API BETA",
				"Terms of service",
				new Contact("Javier Benavides", "", "javierhbr@gmail.com"),
				"License of API", "API license URL", Collections.emptyList());
	}
}

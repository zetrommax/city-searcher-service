package com.gendra.cities.config;

import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

@Generated
@Configuration
@EnableSwagger2
public class SwaggerConfig {


	@Autowired
	BuildProperties buildProperties;
	
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)        		
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gendra.cities.controller"))
                .paths(regex("/.*"))
                .build()           
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false);
    }
  
    
    
    private ApiInfo apiInfo() {
        return new ApiInfo(
          "cities-service",
          "Searcher of cities by name and coodinates",
          buildProperties.getVersion(), 
          "Terms of service",
          new Contact("Sergio Garcia","","zetromax@gmail.com"),
          "License of API", "API license URL", Collections.emptyList());
    }
	
	
	
}

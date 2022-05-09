package com.gendra.cities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class CitiesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitiesServiceApplication.class, args);
	}

}

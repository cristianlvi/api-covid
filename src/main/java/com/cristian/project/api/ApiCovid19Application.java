package com.cristian.project.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiCovid19Application {

	public static void main(String[] args) {
		SpringApplication.run(ApiCovid19Application.class, args);
	}

}

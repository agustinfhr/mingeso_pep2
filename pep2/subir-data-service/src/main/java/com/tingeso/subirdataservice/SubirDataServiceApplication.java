package com.tingeso.subirdataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SubirDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubirDataServiceApplication.class, args);
	}

}

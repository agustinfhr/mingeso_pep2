package com.tingeso.subirvalorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SubirValorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubirValorServiceApplication.class, args);
	}

}

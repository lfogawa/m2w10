package com.devinhouse.m2s10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class M2s10Application {

	public static void main(String[] args) {
		SpringApplication.run(M2s10Application.class, args);
	}

}

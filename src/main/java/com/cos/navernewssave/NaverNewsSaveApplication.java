package com.cos.navernewssave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class NaverNewsSaveApplication {

	public static void main(String[] args) {
		SpringApplication.run(NaverNewsSaveApplication.class, args);
	}

}

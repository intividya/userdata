package com.demo.userdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class UserdataApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserdataApplication.class, args);
	}

}

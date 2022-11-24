package com.demo.userdata;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserdataApplication {

	public static void main(String[] args) {
		System.out.println(Instant.now());
		//System.out.println(LocalDateTime.now(ZoneId.of("UTC")));
		SpringApplication.run(UserdataApplication.class, args);
	}

}

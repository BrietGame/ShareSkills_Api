package com.shareskills.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ShareskillsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShareskillsApplication.class, args);
	}

}

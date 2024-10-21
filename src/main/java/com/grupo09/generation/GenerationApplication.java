package com.grupo09.generation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.grupo09.generation.repository")
public class GenerationApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenerationApplication.class, args);
	}

}

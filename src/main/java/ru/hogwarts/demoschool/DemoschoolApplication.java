package ru.hogwarts.demoschool;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class DemoschoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoschoolApplication.class, args);
	}
}
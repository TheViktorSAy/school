package ru.hogwarts.demoschool;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//http://localhost:8080/swagger-ui/index.html#/

@SpringBootApplication
@OpenAPIDefinition
public class DemoschoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoschoolApplication.class, args);
	}

}

package com.example.demo8;

import com.example.demo8.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
public class Demo8Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo8Application.class, args);
	}

}

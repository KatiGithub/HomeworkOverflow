package com.homeworkoverflow.homeworkoverflowbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@EnableWebMvc
public class HomeworkoverflowBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeworkoverflowBackendApplication.class, args);
	}

	// public WebMvcConfigurer corsConfigurer() {
	// 	return new WebMvcConfigurer(){
	// 		@Override
	// 		public void addCorsMappings(CorsRegistry registry) {
	// 			registry.addMapping("/**")
	// 					.allowedOrigins("**")
	// 					.allowedMethods("GET, POST")
	// 					.allowCredentials(true).maxAge(86400);
	// 		}
	// 	};
	// }

}

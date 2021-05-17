package com.homeworkoverflow.homeworkoverflowbackend;

import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {
	SecurityAutoConfiguration.class
})
@EnableJpaRepositories
public class HomeworkoverflowBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeworkoverflowBackendApplication.class, args);
		
	}

}

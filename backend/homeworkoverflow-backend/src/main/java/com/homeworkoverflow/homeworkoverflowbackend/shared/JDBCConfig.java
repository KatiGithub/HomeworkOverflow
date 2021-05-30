package com.homeworkoverflow.homeworkoverflowbackend.shared;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages = "com.homeoverflow.homeworkoverflowbackend.repositories")
public class JDBCConfig {

    @Bean
    public DataSource postgreSQLDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://127.0.0.1:5432/homeworkoverflow");
        dataSource.setUsername("postgres");
        dataSource.setPassword("music_2001");

        return dataSource;
    }
}

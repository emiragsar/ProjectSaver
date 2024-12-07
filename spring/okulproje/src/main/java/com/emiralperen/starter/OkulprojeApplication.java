package com.emiralperen.starter;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.emiralperen"})
@ComponentScan(basePackages = {"com.emiralperen"} )
@EnableJpaRepositories(basePackages = {"com.emiralperen"})
public class OkulprojeApplication {

	public static void main(String[] args) {
		SpringApplication.run(OkulprojeApplication.class, args);
	}

}

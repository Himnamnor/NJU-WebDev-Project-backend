package com.nagisa.furukawa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.nagisa.furukawa"})
@EnableJpaRepositories(basePackages = "com.nagisa.furukawa.Repository")
@EntityScan(basePackages = "com.nagisa.furukawa.PO")

public class FurukawaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FurukawaApplication.class, args);
	}

}

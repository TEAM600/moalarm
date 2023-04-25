package com.team600.moalarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class MoalarmApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoalarmApplication.class, args);
	}

}

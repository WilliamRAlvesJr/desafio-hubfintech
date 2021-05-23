package com.william.hubfintech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.william.hubfintech"})
public class HubFintechApplication {

	public static void main(String[] args) {

		SpringApplication.run(HubFintechApplication.class, args);
	}

}

package com.andersen.country_browser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.andersen.country_browser.model.entity")
public class CountryBrowserApplication {

	public static void main(String[] args) {
		SpringApplication.run(CountryBrowserApplication.class, args);
	}

}

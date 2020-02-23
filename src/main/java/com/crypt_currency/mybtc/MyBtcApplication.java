package com.crypt_currency.mybtc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching
@EnableRetry
public class MyBtcApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBtcApplication.class, args);
	}

	@Bean
	public RestTemplate createTemplate() {
		return new RestTemplate();
	}
}

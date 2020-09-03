package com.dsg.webhook.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class DemoApplication {

	public static void main(String[] args) {
		log.info("stating app");
		SpringApplication.run(DemoApplication.class, args);
		log.info("app started");
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}

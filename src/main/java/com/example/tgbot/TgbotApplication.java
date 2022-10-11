package com.example.tgbot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import java.net.*;


@SpringBootApplication
@EnableScheduling
public class TgbotApplication {
	@Bean
	public RestTemplate getRestTemplate() {
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.50.71.254", 3128));
		SimpleClientHttpRequestFactory request = new SimpleClientHttpRequestFactory();
		request.setProxy(proxy);
		return new RestTemplate();
	}


	public static void main(String[] args) throws IOException {

		SpringApplication.run(TgbotApplication.class, args);
	}
}
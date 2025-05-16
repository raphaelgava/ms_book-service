package com.book_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableFeignClients //Permite que você defina clientes HTTP de forma mais simples e legível, utilizando anotações para especificar endpoints. O Feign pode trabalhar em conjunto com a descoberta de serviços, permitindo que você chame serviços registrados usando seus nomes.
public class BookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

}

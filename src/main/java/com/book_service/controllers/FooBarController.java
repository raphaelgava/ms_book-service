package com.book_service.controllers;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Tag(name = "Foo bar")
@RestController
@RequestMapping("book-service")
public class FooBarController {
    private Logger logger = LoggerFactory.getLogger(FooBarController.class);


    @Operation(summary = "Test of resilience4j")
    @GetMapping("/foo-bar")
    //https://resilience4j.readme.io/docs/getting-started
    @Retry(name = "foo-bar-retry", fallbackMethod = "fallbackMethod") //se não existe configuração, o default é 3
    //@CircuitBreaker -> Caso tiver muitos erros, ele passa a ignorar as requisições por um tempo
    //@CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod") //powershell -> while(1){curl http://localhost:8765/book-service/foo-bar; sleep 0.1}
    //@RateLimiter(name= "default") // Limitando quantidade de requisições
    //@Bulkhead(name = "default") // Determina a quantidade de requisições concorrentes
    public String fooBar(){
        logger.info("Request to foo-bar is received!");
        var response = new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class); //Forçando o erro
        return response.getBody().toString();
    }

    public String fallbackMethod(Exception ex){
        return "fallbackMethod foo-bar: " + ex.toString();
    }
}

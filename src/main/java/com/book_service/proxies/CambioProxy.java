package com.book_service.proxies;

import com.book_service.dtos.CambioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

//@FeignClient(name = "ms-cambio-service", url = "localhost:8001") //url = localhost:8000, localhost:8001, ... Para determinar quais server irão fazer parte via hardcode
@FeignClient(name = "ms-cambio-service") //Para deixar o Feign + Eureca resolverem via naming-server (name tem que ser exatamente igual ao spring.application.name do projeto que vai ser acessado)
//é usada para declarar um cliente HTTP que se comunica com outros serviços de forma declarativa. O Feign é uma biblioteca que simplifica a criação de clientes HTTP e é frequentemente usada em aplicações baseadas em microserviços.
public interface CambioProxy {

    @GetMapping(value="/cambio-service/{amount}/{from}/{to}")
    public CambioDTO getCambio(@PathVariable("amount") Double amount,
                               @PathVariable("from") String from,
                               @PathVariable("to") String to);
}

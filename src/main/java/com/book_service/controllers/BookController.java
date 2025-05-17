package com.book_service.controllers;

import com.book_service.dtos.CambioDTO;
import com.book_service.models.Book;
import com.book_service.proxies.CambioProxy;
import com.book_service.repositories.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Tag(name = "Book endpoint")
@RestController
@RequestMapping("book-service")
public class BookController {

    @Autowired
    private Environment environment;

    @Autowired
    private BookRepository repository;

    @Autowired
    private CambioProxy proxy;

    //Sem utilizar o Openfeign
//    @GetMapping(value = "/{id}/{currency}")
//    public Book findBook(@PathVariable("id") Long id,
//                         @PathVariable("currency") String currency){
//
//        var port = environment.getProperty("local.server.port");
//
//        var book = repository.findById(id);
//        if (book.isEmpty()){
//            throw  new RuntimeException("Book not found");
//        }
//
//        HashMap<String, String> params = new HashMap<>();
//        params.put("amount", book.get().getPrice().toString());
//        params.put("from", "USD");
//        params.put("to", currency);
//
//        var response = new RestTemplate().getForEntity("http://localhost:8000/cambio-service/{amount}/{from}/{to}", CambioDTO.class, params);
//        var cambio = response.getBody();
//
//        book.get().setEnvironment(port);
//        book.get().setPrice(cambio.getConvertedValue());
//
//        return book.get();
//    }


    //Usando Openfeign
    @Operation(summary = "Find a specific book by your ID")
    @GetMapping(value = "/{id}/{currency}")
    public Book findBook(@PathVariable("id") Long id,
                         @PathVariable("currency") String currency){

        var port = environment.getProperty("local.server.port");

        var book = repository.findById(id);
        if (book.isEmpty()){
            throw  new RuntimeException("Book not found");
        }

        var cambio = proxy.getCambio(book.get().getPrice(), "USD", currency);

        //Add --server.port=8101 in: Run -> Edit Configurations -> Environment Variables
        book.get().setEnvironment("Book por: " + port + " FEIGN - Cambio port: " + cambio.getEnvironment());
        book.get().setPrice(cambio.getConvertedValue());

        return book.get();
    }

}

package com.book_service.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity(name = "book")
//Implementar Serializable é essencial quando você precisa persistir o estado de um objeto ou transferi-lo entre diferentes contextos. Isso torna a manipulação de dados mais flexível e eficiente em aplicações Java.
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "author", nullable = false, length = 180)
    private String author;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE) //Especificando para o banco qual o tipo de dado será persistido
    private Date launchDate;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false, length = 250)
    private String title;
    @Transient //Esse item não vai ser persistido
    private String currency;
    @Transient //Esse item não vai ser persistido
    private String environment;

    public Book() {
    }

    public Book(Long id, String author, String title, Date launchDate, Double price, String currency, String environment) {
        this.id = id;
        this.author = author;
        this.launchDate = launchDate;
        this.price = price;
        this.title = title;
        this.currency = currency;
        this.environment = environment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}

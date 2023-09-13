package com.example.prac3.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Field name shouldn't be empty!")
    private String name;
    @NotBlank(message = "Field author shouldn't be empty!")
    private String author;
    @NotBlank(message = "Field out_date shouldn't be empty!")
    private String out_date;


    public Book(long id, String name, String author, String out_date) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.out_date = out_date;
    }

    public Book() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getOut_date() {
        return out_date;
    }

    public void setOut_date(String out_date) {
        this.out_date = out_date;
    }
}

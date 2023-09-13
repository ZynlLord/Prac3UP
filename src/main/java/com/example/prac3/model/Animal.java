package com.example.prac3.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "animals")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Field Name shouldn't be empty!")
    private String name;


    @Min(message = "Field should be more or equals 0", value = 0)
    private int age;
    @NotBlank(message = "Field Type shouldn't be empty!")
    private String type;


    public Animal() {
    }

    public Animal(long id, String name, int age, String type) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.type = type;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

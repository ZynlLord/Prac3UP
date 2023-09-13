package com.example.prac3.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "displays")
public class Display {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Field name shouldn't be empty!")
    private String name;
    @NotBlank(message = "Field firm shouldn't be empty!")
    private String firm;

    @Min(message = "Field cost should be >= 0", value = 0)
    private double cost;

    public Display(long id, String name, String firm, double cost) {
        this.id = id;
        this.name = name;
        this.firm = firm;
        this.cost = cost;
    }

    public Display(){}

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

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}

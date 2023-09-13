package com.example.prac3.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "phones")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Field name shouldn't be empty!")
    private String name;
    @NotBlank(message = "Field system shouldn't be empty!")
    private String system;

    @Min(message = "Cost can't be < 0", value = 0)
    private double cost;


    public Phone(long id, String name, String system, double cost) {
        this.id = id;
        this.name = name;
        this.system = system;
        this.cost = cost;
    }

    public Phone(){}

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

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}

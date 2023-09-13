package com.example.prac3.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Field name shouldn't be empty!")
    private String name;
    @Min(message = "Field power should be > 0", value = 1)
    private double cost;

    @Min(message = "Field power should be > 0", value = 1)
    private int power;


    public Car(long id, String name, double cost, int power) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.power = power;
    }

    public Car(){}

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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}

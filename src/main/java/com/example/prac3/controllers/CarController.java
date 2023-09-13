package com.example.prac3.controllers;

import com.example.prac3.model.Car;
import com.example.prac3.repositories.CarRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarRepository carRepository;

    @Autowired
    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/list")
    public String listCars(Model model) {
        model.addAttribute("cars", carRepository.findAll());
        return "cars/list";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("car", new Car());
        return "cars/add-car";
    }

    @PostMapping("/addcar")
    public String addCar(@Valid Car car, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "cars/add-car";
        }

        carRepository.save(car);
        model.addAttribute("cars", carRepository.findAll());

        return "cars/list";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid car ID: " + id));
        model.addAttribute("car", car);
        return "cars/edit-car";
    }

    @PostMapping("/edit/{id}")
    public String updateCar(@PathVariable("id") long id, @Valid Car car, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "cars/edit-car";
        }

        car.setId(id);
        carRepository.save(car);
        return "redirect:/cars/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable("id") long id, Model model) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid car ID: " + id));
        carRepository.delete(car);
        return "redirect:/cars/list";
    }

}

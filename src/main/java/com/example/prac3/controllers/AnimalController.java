package com.example.prac3.controllers;

import com.example.prac3.model.Animal;
import com.example.prac3.repositories.AnimalRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/animals")
public class AnimalController {

    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @GetMapping("/list")
    public String listAnimals(Model model) {
        model.addAttribute("animals", animalRepository.findAll());
        return "animals/list";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("animal", new Animal());
        return "animals/add-animal";
    }

    @PostMapping("/addanimal")
    public String addAnimal(@Valid Animal animal, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "animals/add-animal";
        }

        if (animal.getAge() < 0) {
            result.rejectValue("age", "error.age", "Age should be more or equals 0");
            return "animals/add-animal";
        }

        animalRepository.save(animal);
        model.addAttribute("animals", animalRepository.findAll());

        return "animals/list";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid animal ID: " + id));
        model.addAttribute("animal", animal);
        return "animals/edit-animal";
    }

    @PostMapping("/edit/{id}")
    public String updateAnimal(@PathVariable("id") long id, @Valid Animal animal, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "animals/edit-animal";
        }

        if (animal.getAge() < 0) {
            result.rejectValue("age", "error.age", "Возраст должен быть больше или равен 0");
            return "animals/edit-animal";
        }

        animal.setId(id);
        animalRepository.save(animal);
        return "redirect:/animals/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteAnimal(@PathVariable("id") long id, Model model) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid animal ID: " + id));
        animalRepository.delete(animal);
        return "redirect:/animals/list";
    }

    @GetMapping("/search")
    public String searchAnimals(@RequestParam(name = "name", required = false) String name, Model model) {
        List<Animal> animals = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            animals = animalRepository.findByNameContaining(name);
        } else {
            animals = animalRepository.findAll();
        }

        model.addAttribute("animals", animals);
        model.addAttribute("searchName", name);

        return "animals/list";
    }
}


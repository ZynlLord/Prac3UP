package com.example.prac3.controllers;

import com.example.prac3.model.Phone;
import com.example.prac3.repositories.PhoneRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/phones")
public class PhoneController {

    private final PhoneRepository phoneRepository;

    @Autowired
    public PhoneController(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @GetMapping("/list")
    public String listPhones(Model model) {
        model.addAttribute("phones", phoneRepository.findAll());
        return "phones/list";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("phone", new Phone());
        return "phones/add-phone";
    }

    @PostMapping("/addphone")
    public String addPhone(@Valid Phone phone, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "phones/add-phone";
        }

        if (phone.getCost() < 0) {
            result.rejectValue("cost", "error.cost", "Cost should be more or equals 0");
            return "phones/add-phone";
        }

        phoneRepository.save(phone);
        model.addAttribute("phones", phoneRepository.findAll());

        return "phones/list";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Phone phone = phoneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid phone ID: " + id));
        model.addAttribute("phone", phone);
        return "phones/edit-phone";
    }

    @PostMapping("/edit/{id}")
    public String updatePhone(@PathVariable("id") long id, @Valid Phone phone, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "phones/edit-phone";
        }

        if (phone.getCost() < 0) {
            result.rejectValue("cost", "error.cost", "Cost should be more or equals 0");
            return "phones/edit-phone";
        }

        phone.setId(id);
        phoneRepository.save(phone);
        return "redirect:/phones/list";
    }

    @GetMapping("/delete/{id}")
    public String deletePhone(@PathVariable("id") long id, Model model) {
        Phone phone = phoneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid phone ID: " + id));
        phoneRepository.delete(phone);
        return "redirect:/phones/list";
    }


}

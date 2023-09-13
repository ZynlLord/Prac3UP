package com.example.prac3.controllers;

import com.example.prac3.model.Display;
import com.example.prac3.repositories.DisplayRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/displays")
public class DisplayController {

    private final DisplayRepository displayRepository;

    @Autowired
    public DisplayController(DisplayRepository displayRepository) {
        this.displayRepository = displayRepository;
    }

    @GetMapping("/list")
    public String listDisplays(Model model) {
        model.addAttribute("displays", displayRepository.findAll());
        return "displays/list";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("display", new Display());
        return "displays/add-display";
    }

    @PostMapping("/adddisplay")
    public String addDisplay(@Valid Display display, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "displays/add-display";
        }

        displayRepository.save(display);
        model.addAttribute("displays", displayRepository.findAll());

        return "displays/list";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Display display = displayRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid display ID: " + id));
        model.addAttribute("display", display);
        return "displays/edit-display";
    }

    @PostMapping("/edit/{id}")
    public String updateDisplay(@PathVariable("id") long id, @Valid Display display, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "displays/edit-display";
        }

        display.setId(id);
        displayRepository.save(display);
        return "redirect:/displays/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteDisplay(@PathVariable("id") long id, Model model) {
        Display display = displayRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid display ID: " + id));
        displayRepository.delete(display);
        return "redirect:/displays/list";
    }
}

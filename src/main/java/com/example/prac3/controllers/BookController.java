package com.example.prac3.controllers;

import com.example.prac3.model.Book;
import com.example.prac3.repositories.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/list")
    public String listBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "books/list";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/add-book";
    }

    @PostMapping("/addbook")
    public String addBook(@Valid Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "books/add-book";
        }

        bookRepository.save(book);
        model.addAttribute("books", bookRepository.findAll());

        return "books/list";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + id));
        model.addAttribute("book", book);
        return "books/edit-book";
    }

    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable("id") long id, @Valid Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "books/edit-book";
        }

        book.setId(id);
        bookRepository.save(book);
        return "redirect:/books/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") long id, Model model) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + id));
        bookRepository.delete(book);
        return "redirect:/books/list";
    }


}

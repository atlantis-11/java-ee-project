package com.example.javaeeproject.controller;

import com.example.javaeeproject.entity.Book;
import com.example.javaeeproject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/list")
    public String listBooks(Model model) {
        List<Book> books = bookService.findAll();

        model.addAttribute("books", books);

        return "list-books";
    }
}

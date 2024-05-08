package com.example.javaeeproject.controller;

import com.example.javaeeproject.entity.*;
import com.example.javaeeproject.error.AppException;
import com.example.javaeeproject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

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

    @RequestMapping(value = "/list", params = "title", method = RequestMethod.GET)
    public String listBooks(Model model, @RequestParam String title) {
        List<Book> books = bookService.findByTitle(title);

        model.addAttribute("books", books);

        return "list-books";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable int id, Model model, Principal principal) {
        Book book = bookService.findByIdAndFetchReviews(id);

        if (book == null) {
            throw new AppException("Book with id=" + id + " was not found");
        }

        Review review = book.getReviews().stream()
            .filter(r -> Objects.equals(r.getUser().getUsername(), principal.getName()))
            .findFirst().orElse(null);

        String reviewContent = review == null ? null : review.getContent();

        model.addAttribute("book", book);
        model.addAttribute("reviewContent", reviewContent);

        return "book-page";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("mode", "add");

        return "book-form";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.saveAndFlush(book);

        return "redirect:/books/" + book.getId();
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        Book book = bookService.findById(id);

        if (book == null) {
            throw new AppException("Book with id=" + id + " was not found");
        }

        model.addAttribute("book", book);
        model.addAttribute("mode", "edit");

        return "book-form";
    }

    @PostMapping("/edit")
    public String editBook(@ModelAttribute("book") Book book) {
        bookService.saveAndFlush(book);

        return "redirect:/books/" + book.getId();
    }

    @GetMapping("/{id}/delete")
    public String deleteBook(@PathVariable int id) {
        if (bookService.findById(id) == null) {
            throw new AppException("Book with id=" + id + " was not found");
        }

        bookService.deleteById(id);

        return "redirect:/books/list";
    }
}

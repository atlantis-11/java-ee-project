package com.example.javaeeproject.controller;

import com.example.javaeeproject.entity.*;
import com.example.javaeeproject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static com.example.javaeeproject.utils.Utils.getCurrentUsername;

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

    @GetMapping("/{id}")
    public String showBook(@PathVariable int id, Model model) {
        Book book = bookService.findByIdAndFetchReviews(id);

        Review review = book.getReviews().stream()
            .filter(r -> Objects.equals(r.getUser().getUsername(), getCurrentUsername()))
            .findFirst().orElse(null);

        ReviewDTO reviewDTO = new ReviewDTO(
            id, review == null ? null : review.getContent()
        );

        model.addAttribute("book", book);
        model.addAttribute("reviewDTO", reviewDTO);

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
        bookService.save(book);

        return "redirect:/books/list";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        Book book = bookService.findById(id);

        model.addAttribute("book", book);
        model.addAttribute("mode", "edit");

        return "book-form";
    }

    @PostMapping("/edit")
    public String editBook(@ModelAttribute("book") Book book) {
        bookService.save(book);

        return "redirect:/books/list";
    }

    @GetMapping("/{id}/delete")
    public String deleteBook(@PathVariable int id) {
        bookService.deleteById(id);

        return "redirect:/books/list";
    }
}

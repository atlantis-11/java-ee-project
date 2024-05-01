package com.example.javaeeproject.rest;

import com.example.javaeeproject.entity.Book;
import com.example.javaeeproject.error.RestException;
import com.example.javaeeproject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRestController {
    private final BookService bookService;

    @Autowired
    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookService.findAll();
        return books.stream().map(BookDTO::new).toList();
    }

    @GetMapping("/{id}")
    public BookDTO getBook(@PathVariable int id) {
        Book book = bookService.findById(id);

        if (book == null) {
            throw new RestException(HttpStatus.NOT_FOUND);
        }

        return new BookDTO(book);
    }

    @PostMapping("")
    public BookDTO addBook(@RequestBody Book book) {
        try {
            bookService.saveAndFlush(book);
            return new BookDTO(book);
        } catch (Exception e) {
            throw new RestException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public BookDTO editBook(@PathVariable int id, @RequestBody Book book) {
        if (bookService.findById(id) == null) {
            throw new RestException(HttpStatus.NOT_FOUND);
        }

        try {
            book.setId(id);
            bookService.saveAndFlush(book);

            return new BookDTO(book);
        } catch (Exception e) {
            throw new RestException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public BookDTO deleteBook(@PathVariable int id) {
        Book book = bookService.findById(id);

        if (book == null) {
            throw new RestException(HttpStatus.NOT_FOUND);
        }

        bookService.deleteById(id);

        return new BookDTO(book);
    }
}

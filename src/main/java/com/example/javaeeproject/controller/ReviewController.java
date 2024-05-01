package com.example.javaeeproject.controller;

import com.example.javaeeproject.entity.Review;
import com.example.javaeeproject.entity.ReviewId;
import com.example.javaeeproject.service.BookService;
import com.example.javaeeproject.service.ReviewService;
import com.example.javaeeproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/books/{bookId}/reviews")
public class ReviewController {
    private final BookService bookService;
    private final UserService userService;
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(BookService bookService, UserService userService, ReviewService reviewService) {
        this.bookService = bookService;
        this.userService = userService;
        this.reviewService = reviewService;
    }

    @PostMapping("/add")
    public String addReview(@PathVariable int bookId,
                            @RequestParam String reviewContent,
                            Principal principal) {
        Review review = new Review(
            userService.findByUsername(principal.getName()),
            bookService.findById(bookId),
            reviewContent
        );

        reviewService.save(review);

        return "redirect:/books/" + bookId;
    }

    @GetMapping("/delete")
    public String deleteReview(@PathVariable int bookId, Principal principal) {
        Review review = reviewService.findByUsernameAndBookId(principal.getName(), bookId);

        if (review != null) {
            reviewService.deleteById(new ReviewId(review.getUser(), review.getBook()));
        }

        return "redirect:/books/" + bookId;
    }
}

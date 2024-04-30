package com.example.javaeeproject.controller;

import com.example.javaeeproject.entity.Review;
import com.example.javaeeproject.entity.ReviewDTO;
import com.example.javaeeproject.entity.ReviewId;
import com.example.javaeeproject.service.BookService;
import com.example.javaeeproject.service.ReviewService;
import com.example.javaeeproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/reviews")
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
    public String addReview(@ModelAttribute("review") ReviewDTO reviewDTO, Principal principal) {
        Review review = new Review(
            userService.findByUsername(principal.getName()),
            bookService.findById(reviewDTO.getBookId()),
            reviewDTO.getContent()
        );

        reviewService.save(review);

        return "redirect:/books/" + reviewDTO.getBookId();
    }

    @RequestMapping(value = "/delete", params = "bookId", method = RequestMethod.GET)
    public String deleteReview(@RequestParam int bookId, Principal principal) {
        Review review = reviewService.findByUsernameAndBookId(principal.getName(), bookId);

        if (review != null) {
            reviewService.deleteById(new ReviewId(review.getUser(), review.getBook()));
        }

        return "redirect:/books/" + bookId;
    }
}

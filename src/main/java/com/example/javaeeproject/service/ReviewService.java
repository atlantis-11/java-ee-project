package com.example.javaeeproject.service;

import com.example.javaeeproject.entity.Review;
import com.example.javaeeproject.entity.ReviewId;

public interface ReviewService {
    Review findByUsernameAndBookId(String username, int bookId);

    void save(Review review);

    void deleteById(ReviewId reviewId);
}

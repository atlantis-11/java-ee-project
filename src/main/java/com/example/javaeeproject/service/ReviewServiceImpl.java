package com.example.javaeeproject.service;

import com.example.javaeeproject.dao.ReviewRepository;
import com.example.javaeeproject.entity.Review;
import com.example.javaeeproject.entity.ReviewId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review findByUsernameAndBookId(String username, int bookId) {
        return reviewRepository.findByUsernameAndBookId(username, bookId);
    }

    @Override
    public void save(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public void deleteById(ReviewId reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}

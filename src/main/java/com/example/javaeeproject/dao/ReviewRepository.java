package com.example.javaeeproject.dao;

import com.example.javaeeproject.entity.Review;
import com.example.javaeeproject.entity.ReviewId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, ReviewId>  {
    @Query("select r from Review r where r.user.username = :username and r.book.id = :bookId")
    Review findByUsernameAndBookId(@Param("username") String username, @Param("bookId") int bookId);
}

package com.example.javaeeproject.dao;

import com.example.javaeeproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("select u from User u join fetch u.reviews where u.username = :username")
    User findByUsernameAndFetchReviews(@Param("username") String username);
}

package com.example.javaeeproject.service;

import com.example.javaeeproject.entity.User;

public interface UserService {
    User findByUsername(String username);

    User findByUsernameAndFetchReviews(String username);
}

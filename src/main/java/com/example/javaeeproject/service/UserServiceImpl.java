package com.example.javaeeproject.service;

import com.example.javaeeproject.dao.UserRepository;
import com.example.javaeeproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findById(username).orElse(null);
    }

    @Override
    public User findByUsernameAndFetchReviews(String username) {
        return userRepository.findByUsernameAndFetchReviews(username);
    }
}

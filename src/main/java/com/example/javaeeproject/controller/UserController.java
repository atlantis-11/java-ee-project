package com.example.javaeeproject.controller;

import com.example.javaeeproject.entity.User;
import com.example.javaeeproject.error.AppException;
import com.example.javaeeproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public String showUser(@PathVariable String username, Model model) {
        User user = userService.findByUsernameAndFetchReviews(username);

        if (user == null) {
            throw new AppException("User with username=" + username + " was not found");
        }

        model.addAttribute("user", user);

        return "user-page";
    }

    @GetMapping("/me")
    public String showUser(Principal principal) {
        return "forward:/users/" + principal.getName();
    }
}

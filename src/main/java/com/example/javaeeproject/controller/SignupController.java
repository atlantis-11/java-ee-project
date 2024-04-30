package com.example.javaeeproject.controller;

import com.example.javaeeproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {
    private final UserDetailsManager userDetailsManager;

    @Autowired
    public SignupController(UserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());

        return "signup-form";
    }

    @PostMapping("/signup")
    public String processSignup(User user) {
        UserDetails userDetails = org.springframework.security.core.userdetails.User
            .withUsername(user.getUsername())
            .password("{bcrypt}"
                + new BCryptPasswordEncoder().encode(user.getPassword()))
            .roles("USER")
            .build();

        userDetailsManager.createUser(userDetails);

        return "redirect:/login";
    }
}

package com.example.javaeeproject.controller;

import com.example.javaeeproject.error.AppException;
import com.example.javaeeproject.error.RestException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(AppException.class)
    public String handleAppException(AppException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error-page";
    }

    @ExceptionHandler(RestException.class)
    public ResponseEntity<?> handleRestException(RestException e) {
        return new ResponseEntity<>(e.getStatus());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public String handleNoResourceFoundException(NoResourceFoundException e, Model model) {
        model.addAttribute("errorMessage", "Page not found");
        return "error-page";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("errorMessage", "There was an unexpected error");
        return "error-page";
    }
}

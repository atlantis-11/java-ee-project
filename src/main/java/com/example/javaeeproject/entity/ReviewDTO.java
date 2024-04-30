package com.example.javaeeproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ReviewDTO {
    private int bookId;
    private String content;
}

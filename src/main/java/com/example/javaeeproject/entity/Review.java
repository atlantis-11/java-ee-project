package com.example.javaeeproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "review")
@IdClass(ReviewId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "review")
    private String review;
}
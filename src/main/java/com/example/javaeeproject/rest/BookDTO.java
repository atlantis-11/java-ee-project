package com.example.javaeeproject.rest;

import com.example.javaeeproject.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class BookDTO {
    public BookDTO(Book book) {
        id = book.getId();
        title = book.getTitle();
        author = book.getAuthor();
        description = book.getDescription();
        coverUrl = book.getCoverUrl();
    }

    private int id;
    private String title;
    private String author;
    private String description;
    private String coverUrl;
}

package com.example.javaeeproject.service;

import com.example.javaeeproject.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    List<Book> findByTitle(String title);

    Book findById(int id);

    Book findByIdAndFetchReviews(int id);

    void save(Book book);

    void saveAndFlush(Book book);

    void deleteById(int id);
}

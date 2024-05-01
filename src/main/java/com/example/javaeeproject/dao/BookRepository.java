package com.example.javaeeproject.dao;

import com.example.javaeeproject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("select b from Book b left join fetch b.reviews where b.id = :id")
    Book findByIdAndFetchReviews(@Param("id") int id);

    @Query("select b from Book b where b.title ilike %:title%")
    List<Book> findByTitle(@Param("title") String title);
}

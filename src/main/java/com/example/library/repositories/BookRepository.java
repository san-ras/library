package com.example.library.repositories;

import com.example.library.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository  <Book, Integer> {

    List<Book> findByCategoryContainingIgnoreCase(String title);
}

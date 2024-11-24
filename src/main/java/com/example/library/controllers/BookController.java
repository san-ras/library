package com.example.library.controllers;

import com.example.library.dto.BookDTO;
import com.example.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping()
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) {
        BookDTO createdBookDTO = bookService.addBook(bookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBookDTO);
    }

    @GetMapping()
    public ResponseEntity<List<BookDTO>> addBook() {
        List<BookDTO> listOfBooks = bookService.getBooks();
        return ResponseEntity.status(HttpStatus.CREATED).body(listOfBooks);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Successfully deleted");
    }


    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO updatedBookDTO, @PathVariable int id) {
        BookDTO bookDTO = bookService.updateBook(updatedBookDTO, id);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookDTO>> getBooksByCategory(@RequestParam String title) {
        List<BookDTO> books = bookService.getBooksByCategory(title);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

}

package com.example.library.services;

import com.example.library.dto.BookDTO;
import com.example.library.entities.Book;
import com.example.library.exceptions.BookNotFoundException;
import com.example.library.exceptions.InvalidBookException;
import com.example.library.repositories.BookRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@NoArgsConstructor
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public BookDTO addBook(BookDTO bookDTO) {
        validateBook(bookDTO);
        Book book = convertToBook(bookDTO);
        book = bookRepository.save(book);

        return convertToDto(book);
    }


    public List<BookDTO> getBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(this::convertToDto)
                .toList();
    }

    public void deleteBook(int id) {
        Book bookToDelete = bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        bookRepository.delete(bookToDelete);
    }

    public BookDTO updateBook(BookDTO updatedBookDTO, int id) {
        validateBook(updatedBookDTO);
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        existingBook.setTitle(updatedBookDTO.getTitle());
        existingBook.setAuthor(updatedBookDTO.getAuthor());
        existingBook.setCategory(updatedBookDTO.getCategory());
        existingBook.setPrice(updatedBookDTO.getPrice());
        existingBook.setCover(updatedBookDTO.getCover());
        existingBook.setReserved(updatedBookDTO.isReserved());

        Book updatedBook = bookRepository.save(existingBook);
        return convertToDto(updatedBook);
    }

    public List<BookDTO> getBooksByTitle(String title) {
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase(title);
        return books.stream().map(this::convertToDto).toList();
    }


    private BookDTO convertToDto(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setCover(book.getCover());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setCategory(book.getCategory());
        return bookDTO;
    }

    private Book convertToBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setCategory(bookDTO.getCategory());
        book.setPrice(bookDTO.getPrice());
        book.setCover(bookDTO.getCover());
        return book;
    }

    private static void validateBook(BookDTO bookDTO) {
        if (bookDTO == null) {
            throw new InvalidBookException("Book details must be provided");
        }

        if (bookDTO.getTitle() == null || bookDTO.getTitle().isEmpty()) {
            throw new InvalidBookException("Book title is required");
        }

        if (bookDTO.getCategory() == null || bookDTO.getCategory().isEmpty()) {
            throw new InvalidBookException("Book category is required");
        }

        if (bookDTO.getPrice() == null || bookDTO.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidBookException("Price must be greater than zero");
        }
    }

}



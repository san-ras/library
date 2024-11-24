package com.example.library.exceptions;

public class BookNotFoundException extends RuntimeException {

        public BookNotFoundException() {
        super("Book with specified id does not exist");
        }
    }

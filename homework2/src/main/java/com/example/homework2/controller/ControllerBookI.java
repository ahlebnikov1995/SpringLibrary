package com.example.homework2.controller;

import com.example.homework2.models.Book;
import com.example.homework2.models.dto.BookDto;

import java.util.List;

public interface ControllerBookI {
    List<BookDto> find(String name);
    List<BookDto> finds(String name);
    Book add(String name, String a, String g);
    List<BookDto> findAllBooks();
    List<BookDto> findAllByAuthor(String author);
    List<BookDto> findAllByGenre(String genre);
    Book deleteBook(Long id, String name, String a, String g);
    BookDto updateBook(Book book);
}

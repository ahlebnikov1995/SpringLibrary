package com.example.homework2.service;

import com.example.homework2.models.Author;
import com.example.homework2.models.Book;
import com.example.homework2.models.Genre;
import com.example.homework2.models.dto.BookDto;

import java.util.List;

public interface ServiceBookI {
    List<Book> findByName(String name);
    Book findById(long id);
    public List<Book> findByNameAndSort(String name);
    void addBook(Book book);
    List<Book> findAllBook();
    List<Book> findAllByAuthor(String name);
    List<Book> findAllByGenre(String name);
    Book deleteBook(long id, long aid);
    Book updateBook(Book book);

}

package com.example.homework2.controller;

import com.example.homework2.models.Author;
import com.example.homework2.models.Book;
import com.example.homework2.models.Genre;
import com.example.homework2.models.dto.BookDto;
import com.example.homework2.service.ServiceBookI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
@AllArgsConstructor
public class ControllerBookC implements ControllerBookI {

    private ServiceBookI service;


    @Override
    @GetMapping("/books/{name}")
    public List<BookDto> find(@PathVariable String name) {
       List<Book> books = service.findByName(name);
       List<BookDto> booksDto = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            booksDto.add(BookDto.toDto(books.get(i)));
        }
        return booksDto;
    }

    @Override
    @GetMapping("/books/sort/{name}")
    public List<BookDto> finds(@PathVariable String name) {
        List<Book> books = service.findByNameAndSort(name);
        List<BookDto> booksDto = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            booksDto.add(BookDto.toDto(books.get(i)));
        }
        return booksDto;
    }


    @Override
    @PostMapping("/book/{name}/{a}/{g}")
    public Book add(@PathVariable String name, @PathVariable String a, @PathVariable String g) {
        Author author = Author.builder().name(a).build();
        Genre genre = Genre.builder().name(g).build();
        Book book = Book.builder().name(name).author(author).genre(genre).build();
        service.addBook(book);
        return book;
    }


    @Override
    @GetMapping("/books")
    public List<BookDto> findAllBooks() {
        List<BookDto> books = new ArrayList<>();
        List<Book> books1 = service.findAllBook();
        for (int i = 0; i < books1.size(); i++) {
           books.add(BookDto.toDto(books1.get(i)));
        }
        return books;
    }


    @Override
    @GetMapping("/books/ByAuthor/{a}")
    public List<BookDto> findAllByAuthor(@PathVariable String a) {
        List<BookDto> books = new ArrayList<>();
        List<Book> books1 = service.findAllByAuthor(a);
        for (int i = 0; i < books1.size(); i++) {
            books.add(BookDto.toDto(books1.get(i)));
        }
        return books;
    }


    @Override
    @GetMapping("/books/ByGenre/{g}")
    public List<BookDto> findAllByGenre(@PathVariable String g) {
        List<BookDto> books = new ArrayList<>();
        List<Book> books1 = service.findAllByGenre(g);
        for (int i = 0; i < books1.size(); i++) {
            books.add(BookDto.toDto(books1.get(i)));
        }
        return books;
    }

    @Override
    @DeleteMapping("/book/{id}/{name}/{a}/{g}")
    public Book deleteBook(@PathVariable Long id, @PathVariable String name, @PathVariable String a, @PathVariable String g) {
        Author author = Author.builder().name(a).build();
        Genre genre = Genre.builder().name(g).build();
        Book book = Book.builder().id(id).name(name).author(author).genre(genre).build();
        service.deleteBook(book);
        return book;
    }

    @Override
    @PutMapping("/book")
    public BookDto updateBook(@RequestBody Book book) {
        return BookDto.toDto(service.updateBook(book));
    }
}

package com.example.homework2.controller;

import com.example.homework2.models.Author;
import com.example.homework2.models.Book;
import com.example.homework2.models.Genre;
import com.example.homework2.models.dto.BookDto;
import com.example.homework2.service.ServiceBookI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
@AllArgsConstructor
public class ControllerBookC implements ControllerBookI {

    private ServiceBookI service;

    @Transactional(readOnly = true)
    @Override
    @GetMapping("/find/{name}")
    public List<BookDto> find(@PathVariable String name) {
       List<Book> books = service.findByName(name);
       List<BookDto> booksDto = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            booksDto.add(BookDto.toDto(books.get(i)));
        }
        return booksDto;
    }

    @Override
    @GetMapping("/findSort/{name}")
    public List<BookDto> finds(@PathVariable String name) {
        List<Book> books = service.findByNameAndSort(name);
        List<BookDto> booksDto = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            booksDto.add(BookDto.toDto(books.get(i)));
        }
        return booksDto;
    }


    @Override
    @GetMapping("/add/{name}/{a}/{g}")
    public void add(@PathVariable String name, @PathVariable String a, @PathVariable String g) {
        Author author = new Author(a);
        Genre genre = new Genre(g);
        service.addBook(new Book(name, author, genre));
    }


    @Override
    @GetMapping("/findAll")
    public List<BookDto> findAllBooks() {
        List<BookDto> books = new ArrayList<>();
        List<Book> books1 = service.findAllBook();
        for (int i = 0; i < books1.size(); i++) {
           books.add(BookDto.toDto(books1.get(i)));
        }
        return books;
    }


    @Override
    @GetMapping("/findAllBA/{a}")
    public List<BookDto> findAllByAuthor(@PathVariable String a) {
        List<BookDto> books = new ArrayList<>();
        List<Book> books1 = service.findAllByAuthor(a);
        for (int i = 0; i < books1.size(); i++) {
            books.add(BookDto.toDto(books1.get(i)));
        }
        return books;
    }


    @Override
    @GetMapping("/findAllBG/{g}")
    public List<BookDto> findAllByGenre(@PathVariable String g) {
        List<BookDto> books = new ArrayList<>();
        List<Book> books1 = service.findAllByGenre(g);
        for (int i = 0; i < books1.size(); i++) {
            books.add(BookDto.toDto(books1.get(i)));
        }
        return books;
    }

    @Override
    @GetMapping("/delete/{id}/{name}/{a}/{g}")
    public void deleteBook(@PathVariable Long id, @PathVariable String name, @PathVariable String a, @PathVariable String g) {
        Author author = new Author(a);
        Genre genre = new Genre(g);
        service.deleteBook(new Book(id,name,author,genre));
    }
}

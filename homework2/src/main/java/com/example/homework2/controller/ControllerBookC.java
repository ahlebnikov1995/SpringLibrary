package com.example.homework2.controller;

import com.example.homework2.Security.MyUserDetailService;
import com.example.homework2.models.Author;
import com.example.homework2.models.Book;
import com.example.homework2.models.Genre;
import com.example.homework2.models.dto.BookDto;
import com.example.homework2.service.ServiceAuthorI;
import com.example.homework2.service.ServiceBookI;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
@AllArgsConstructor
public class ControllerBookC implements ControllerBookI {

    private ServiceBookI service;
    private MyUserDetailService myUserDetailService;




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
    @PreAuthorize("hasAuthority('author')")
    @PostMapping("/book/{name}/{g}")
        public Book add(@PathVariable String name, @PathVariable String g) {

            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = context.getAuthentication();
            UserDetails principal = (UserDetails) authentication.getPrincipal();

            Author author = (Author) myUserDetailService.loadUserByUsername(principal.getUsername());
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
    @PostAuthorize("returnObject.author.name == authentication.principal.username")
    @DeleteMapping("/book/{id}")
    public Book deleteBook(@PathVariable Long id) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();

        Author author = (Author) myUserDetailService.loadUserByUsername(principal.getUsername());

        return service.deleteBook(id, author.getId());

    }

    //не пускает, но всё равно удаляет

    @Override
    @PreAuthorize("#book.author.name == authentication.principal.username")
    @PutMapping("/book")
    public BookDto updateBook(@RequestBody Book book) {
        return BookDto.toDto(service.updateBook(book));
    }
}

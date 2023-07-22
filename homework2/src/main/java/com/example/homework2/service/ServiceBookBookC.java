package com.example.homework2.service;

import com.example.homework2.dao.DaoAuthor;
import com.example.homework2.dao.DaoBook;
import com.example.homework2.dao.DaoComments;
import com.example.homework2.dao.DaoGenre;
import com.example.homework2.models.Author;
import com.example.homework2.models.Book;
import com.example.homework2.models.Comments;
import com.example.homework2.models.Genre;
import lombok.AllArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static javafx.scene.input.KeyCode.T;


@Service
@AllArgsConstructor
public class ServiceBookBookC implements ServiceBookI {

    private DaoBook daoBook;
    private DaoAuthor daoAuthor;
    private DaoGenre daoGenre;
    private DaoComments daoComments;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public List<Book> findByName(String name) {
        return daoBook.findByName(name);
    }

    @Override
    public Book findById(long id) {
        return daoBook.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findByNameAndSort(String name){
        List<Book> books = daoBook.findByName(name);
        books.sort(new Comparator<Book>() {
            @Override
            public int compare(Book book, Book t1) {
                return ((int)book.getAuthor().getName().charAt(0) - (int)t1.getAuthor().getName().charAt(0));
            }
        });
        return books;
    }

    @Transactional
    @Override
    public void addBook(Book book) {
        try {
            Optional<Genre> result = Optional.ofNullable(daoGenre.findByName(book.getGenre().getName()));
            book.setGenre(result.get());
        }catch (NoSuchElementException e){

        }
        daoBook.saveAndFlush(book);

    }


    @Override
    public List<Book> findAllBook() {
        return daoBook.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findAllByAuthor(String name) {
        return daoBook.findByAuthorName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findAllByGenre(String name) {
        return daoBook.findByGenreName(name);
    }

    @Transactional
    @Override
    public Book deleteBook(long id, long aid) {
        Book book = daoBook.findById(id);
        daoBook.deleteByIdAndAuthor_id(id, aid);
        return book;
        }


    @Transactional
    @Override
    public Book updateBook(Book book) {
       Book book1 = daoBook.findById(book.getId());
       book1.setName(book.getName());


       try {
           Optional<Genre> result = Optional.ofNullable(daoGenre.findByName(book.getGenre().getName()));
           book1.setGenre(result.get());
       }catch (NoSuchElementException e){
           book1.setGenre(Genre.builder().name(book.getGenre().getName()).build());
       }

       daoBook.saveAndFlush(book1);
       return book1;
    }


}

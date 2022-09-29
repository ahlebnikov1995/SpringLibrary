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

import java.util.Comparator;
import java.util.List;


@Service
@AllArgsConstructor
public class ServiceBookBookC implements ServiceBookI {

    private DaoBook daoBook;
    private DaoAuthor daoAuthor;
    private DaoGenre daoGenre;
    private DaoComments daoComments;

    @Transactional(readOnly = true)
    @Override
    public List<Book> findByName(String name) {
        return daoBook.findByName(name);
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
        List<Author> authors = daoAuthor.findByName(book.getAuthor().getName());
        if(authors.size() != 0){
            book.setAuthor(authors.get(0));
        }
        List<Genre> genres = daoGenre.findByName(book.getGenre().getName());
        if(genres.size() != 0){
            book.setGenre(genres.get(0));
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
    public void deleteBook(Book book) {
        List<Author> authors = daoAuthor.findByName(book.getAuthor().getName());
        List<Genre> genres = daoGenre.findByName(book.getGenre().getName());
        if(authors.size()!=0 && genres.size()!=0){
        long aid = daoAuthor.findByName(book.getAuthor().getName()).get(0).getId();
        long gid = daoGenre.findByName(book.getGenre().getName()).get(0).getId();
        if(daoBook.findByIdAndNameAndAuthor_idAndGenre_id(book.getId(),book.getName(),aid,gid).size() != 0) {
            List<Comments> comments = daoBook.findById(book.getId()).getComments();;
            for (int i = 0; i < comments.size(); i++) {
                daoComments.delete(comments.get(i));
            }

            daoBook.delete(book);

            if(daoBook.findByAuthorName(book.getAuthor().getName()).size() == 0){
                daoAuthor.delete(daoAuthor.findByName(book.getAuthor().getName()).get(0));
            }
            if(daoBook.findByGenreName(book.getGenre().getName()).size() == 0){
                daoGenre.delete(daoGenre.findByName(book.getGenre().getName()).get(0));
            }
        }
        }
    }


}

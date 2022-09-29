package com.example.homework2.dao;

import com.example.homework2.models.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaoBook extends JpaRepository<Book,Long> {

    Book findById(long id);

    List<Book> findByName(String name);


    List<Book> findByAuthorName(String name);


    List<Book> findByGenreName(String name);

    @EntityGraph("book_graph")
    List<Book> findAll();


    List<Book> findByIdAndNameAndAuthor_idAndGenre_id(long id, String name, long aid, long gid);




}

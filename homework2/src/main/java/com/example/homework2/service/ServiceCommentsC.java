package com.example.homework2.service;

import com.example.homework2.dao.DaoBook;
import com.example.homework2.dao.DaoComments;
import com.example.homework2.models.Book;
import com.example.homework2.models.Comments;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ServiceCommentsC implements ServiceCommentsI {

    private DaoComments dao;
    private DaoBook daoBook;

    @Transactional
    @Override
    public void addComment(String value, long bid) {
        Book book = daoBook.getById(bid);
        book.getComments().add(Comments.builder().value(value).build());
        daoBook.saveAndFlush(book);
    }
}

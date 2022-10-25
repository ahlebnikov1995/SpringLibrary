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
    public Comments addComment(String value, long bid) {
        Book book = daoBook.getById(bid);
        Comments comments = Comments.builder().value(value).build();
        dao.saveAndFlush(comments);
        book.getComments().add(comments);
        daoBook.saveAndFlush(book);
        return comments;
    }
}

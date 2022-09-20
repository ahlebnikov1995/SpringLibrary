package com.example.homework2.service;

import com.example.homework2.dao.DaoAuthor;
import com.example.homework2.models.Author;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class ServiceAuthorC implements ServiceAuthorI {
    private DaoAuthor daoAuthor;

    @Override
    public List<Author> findAll() {
        return daoAuthor.findAll();
    }
}

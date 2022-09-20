package com.example.homework2.service;

import com.example.homework2.dao.DaoGenre;
import com.example.homework2.models.Genre;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class ServiceGenreC implements ServiceGenreI{

    private DaoGenre daoGenre;


    @Override
    public List<Genre> findAll() {
        return daoGenre.findAll();
    }
}

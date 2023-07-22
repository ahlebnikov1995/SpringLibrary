package com.example.homework2.service;

import com.example.homework2.models.Author;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ServiceAuthorI {
    List<Author> findAll();
    Author registration(Author author);

}

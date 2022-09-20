package com.example.homework2.controller;

import com.example.homework2.service.ServiceGenreI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ControllerGenreC implements ControllerGenreI {
    private ServiceGenreI service;
    @Override
    public void findAll() {
        service.findAll().forEach(genre -> System.out.println(genre.getId() + ". " + genre.getName()));
    }
}

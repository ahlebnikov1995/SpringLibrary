package com.example.homework2.controller;

import com.example.homework2.service.ServiceAuthorI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ControllerAuthorC implements ControllerAuthorI{
    private ServiceAuthorI service;

    @Override
    public void findAll() {
        service.findAll().forEach(author -> System.out.println(author.getId() + ". " + author.getName()));
    }
}

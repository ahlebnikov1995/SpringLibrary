package com.example.homework2.controller;

import com.example.homework2.models.Author;
import com.example.homework2.models.dto.AuthorDto;
import com.example.homework2.service.ServiceAuthorI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class ControllerAuthorC implements ControllerAuthorI{
    private ServiceAuthorI service;



    @Override
    @GetMapping("/authors")
    public List<AuthorDto> findAll() {
       List<Author> authors =  service.findAll();
       List<AuthorDto> dtos = new ArrayList<>();
        for (int i = 0; i < authors.size(); i++) {
            dtos.add(AuthorDto.toDto(authors.get(i)));
        }
       return dtos;
    }

    @Override
    @PostMapping("/registration")
    public Author registration(@RequestBody Author author) {
        return service.registration(author);
    }
}

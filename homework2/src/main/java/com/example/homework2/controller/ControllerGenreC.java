package com.example.homework2.controller;

import com.example.homework2.models.Genre;
import com.example.homework2.models.dto.GenreDto;
import com.example.homework2.service.ServiceGenreI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class ControllerGenreC implements ControllerGenreI {
    private ServiceGenreI service;


    @Override
    @GetMapping("/genres")
    public List<GenreDto> findAll() {
        List<Genre> genres = service.findAll();
        List<GenreDto> dtos = new ArrayList<>();
        for (int i = 0; i < genres.size(); i++) {
          dtos.add(GenreDto.toDto(genres.get(i)));
        }
        return dtos;
    }
}

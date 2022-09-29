package com.example.homework2.controller;

import com.example.homework2.models.dto.GenreDto;

import java.util.List;

public interface ControllerGenreI {
    List<GenreDto> findAll();
}

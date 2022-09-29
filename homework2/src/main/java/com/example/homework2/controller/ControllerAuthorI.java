package com.example.homework2.controller;

import com.example.homework2.models.dto.AuthorDto;

import java.util.List;

public interface ControllerAuthorI {
    List<AuthorDto> findAll();
}

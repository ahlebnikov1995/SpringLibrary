package com.example.homework2.controller;

import com.example.homework2.models.Comments;
import com.example.homework2.service.ServiceCommentsI;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ControllerCommentsC implements ControllerCommentsI {

    private ServiceCommentsI service;

    @Override
    @PostMapping("/Com/{value}/{bid}")
    public Comments addComments(@PathVariable String value, @PathVariable long bid) {
        return service.addComment(value, bid);
    }
}

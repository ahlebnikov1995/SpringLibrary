package com.example.homework2.controller;

import com.example.homework2.models.Comments;

import java.util.List;

public interface ControllerCommentsI {
    Comments addComments(String value, long bid);
}

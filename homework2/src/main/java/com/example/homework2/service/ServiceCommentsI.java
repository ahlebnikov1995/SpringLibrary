package com.example.homework2.service;

import com.example.homework2.models.Book;
import com.example.homework2.models.Comments;

public interface ServiceCommentsI {
    Comments addComment(String value, long id);
}

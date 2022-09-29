package com.example.homework2.dao;

import com.example.homework2.models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DaoComments extends JpaRepository<Comments,Long> {

}

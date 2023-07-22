package com.example.homework2.dao;

import com.example.homework2.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaoAuthor extends JpaRepository<Author,Long> {

   Author findById(long id);

   Author findByName(String name);


}

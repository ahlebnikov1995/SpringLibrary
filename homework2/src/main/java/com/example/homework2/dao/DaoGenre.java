package com.example.homework2.dao;

import com.example.homework2.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaoGenre extends JpaRepository<Genre,Long> {

   List<Genre> findByName(String name);
}

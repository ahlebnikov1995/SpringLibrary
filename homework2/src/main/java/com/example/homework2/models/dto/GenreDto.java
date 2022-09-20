package com.example.homework2.models.dto;

import com.example.homework2.models.Author;
import com.example.homework2.models.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto {
    private long id;

    private String name;

    public Genre toDomain(){
        return new Genre(id, name);
    }

    public static GenreDto toDto(Genre genre){
        return new GenreDto(genre.getId(),genre.getName());
    }
}

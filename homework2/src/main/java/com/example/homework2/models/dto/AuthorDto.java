package com.example.homework2.models.dto;

import com.example.homework2.models.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {

    private long id;

    private String name;

    public Author toDomain(){
       return Author.builder().id(id).name(name).build();
    }

    public static AuthorDto toDto(Author author){
       return new AuthorDto(author.getId(),author.getName());
    }
}

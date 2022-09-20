package com.example.homework2.models.dto;

import com.example.homework2.models.Author;
import com.example.homework2.models.Book;
import com.example.homework2.models.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private long id;

    public String name;

    private AuthorDto author;

    private GenreDto genre;

    public Book toDomain(){
        return new Book(id,name,author.toDomain(),genre.toDomain());
    }

    public static BookDto toDto(Book book){
        return new BookDto(book.getId(),book.getName(),AuthorDto.toDto(book.getAuthor()),GenreDto.toDto(book.getGenre()));
    }

}

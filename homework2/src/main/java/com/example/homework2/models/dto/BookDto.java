package com.example.homework2.models.dto;

import com.example.homework2.models.Author;
import com.example.homework2.models.Book;
import com.example.homework2.models.Comments;
import com.example.homework2.models.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private long id;

    public String name;

    private AuthorDto author;

    private GenreDto genre;

    private List<CommentsDto> comments;


    public Book toDomain(){
        List<Comments> domCom = new ArrayList<>();
        for (int i = 0; i < comments.size(); i++) {
          domCom.add(comments.get(i).toDomain());
        }
        return new Book(id,name,author.toDomain(),genre.toDomain(),domCom);
    }

    public static BookDto toDto(Book book){
        List<CommentsDto> dtoCom = new ArrayList<>();
        for (int i = 0; i < book.getComments().size(); i++) {
            dtoCom.add(CommentsDto.toDto(book.getComments().get(i)));
        }
        return new BookDto(book.getId(),
                            book.getName(),
                            AuthorDto.toDto(book.getAuthor()),
                            GenreDto.toDto(book.getGenre()),
                            dtoCom);
    }

}

package com.example.homework2.models.dto;

import com.example.homework2.models.Book;
import com.example.homework2.models.Comments;
import com.example.homework2.models.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentsDto {
    private long id;

    private String value;



    public Comments toDomain(){
        return new Comments(id, value);
    }

    public static CommentsDto toDto(Comments comments){
        return new CommentsDto(comments.getId(),comments.getValue());
    }
}

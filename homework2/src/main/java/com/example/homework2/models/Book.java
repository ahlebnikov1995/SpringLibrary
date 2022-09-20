package com.example.homework2.models;

import lombok.*;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.data.jpa.repository.EntityGraph;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = "book_graph", attributeNodes = {@NamedAttributeNode("genre"),@NamedAttributeNode("author")})
public class Book {

    public Book(String name, Author author, Genre genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    public String name;

    @ManyToOne(targetEntity = Author.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(targetEntity = Genre.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;
}


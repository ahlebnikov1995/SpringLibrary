package com.example.homework2.models;

import lombok.*;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.data.jpa.repository.EntityGraph;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "book")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = "book_graph", attributeNodes = {@NamedAttributeNode("genre"),@NamedAttributeNode("author")})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    public String name;

    @ManyToOne(targetEntity = Author.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(targetEntity = Genre.class,cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @OneToMany(targetEntity = Comments.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private List<Comments> comments;
}


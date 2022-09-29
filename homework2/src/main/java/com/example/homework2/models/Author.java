package com.example.homework2.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "author")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,unique = true)
    private String name;

  //  @OneToMany(targetEntity = Book.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
  //  @JoinColumn(name = "authors_id")
  //  private List<Book> books;

}

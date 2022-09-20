package com.example.homework2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "author")
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    public Author(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,unique = true)
    private String name;

  //  @OneToMany(targetEntity = Book.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
  //  @JoinColumn(name = "authors_id")
  //  private List<Book> books;

}

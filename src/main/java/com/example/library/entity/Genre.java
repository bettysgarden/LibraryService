package com.example.library.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

//    @ManyToMany(mappedBy = "genres")
//    protected Set<Book> books = new HashSet<>();

    public Genre() {
    }

    public Genre(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
//
//    public Set<Book> getBooks() {
//        return books;
//    }
//
//    public void setBooks(Set<Book> books) {
//        this.books = books;
//    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", title='" + title + '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;
        Genre genre = (Genre) o;
        return id == genre.id && Objects.equals(title, genre.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

}

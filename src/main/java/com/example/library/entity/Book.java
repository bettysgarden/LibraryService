package com.example.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book {
    @Id
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "rating")
    private int rating;

    @Column(name = "date_published")
    private int yearPublished;

    @Column(name = "description")
    private String description;

    @Column(name = "cover")
    private String cover;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "author")
    @JsonIgnore
    private Set<Author> authors = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "book_has_genre",
            joinColumns = { @JoinColumn(name = "book_idbook") },
            inverseJoinColumns = { @JoinColumn(name = "genre_idgenre") })
    private Set<Genre> genres = new HashSet<>();


    public Book() {
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title +
                ", desc=" + description + ", date_published=" + yearPublished +
                ", cover =" + cover + ", rating=" + rating + "]";
    }

    public Book(String title, int rating, int yearPublished, String description, String cover) {
        this.title = title;
        this.rating = rating;
        this.yearPublished = yearPublished;
        this.description = description;
        this.cover = cover;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int date_published) {
        this.yearPublished = date_published;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}

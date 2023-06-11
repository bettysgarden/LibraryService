package com.example.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Year;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "rating")
    private Integer rating;


    @Column(name = "date_published")
    private Timestamp datePublished;

    @Column(name = "description", length = 10000)
    private String description;

    @Column(name = "cover")
    private String cover;

//     the non-owning (inverse side)
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "books")
    @JsonIgnore
    private Set<Author> authors = new HashSet<>();

    // the owning side
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "book_has_genre",
            joinColumns = {@JoinColumn(name = "book_idbook")},
            inverseJoinColumns = {@JoinColumn(name = "genre_idgenre")})
    private Set<Genre> genres = new HashSet<>();

    @OneToMany(mappedBy = "book")
    private Set<Review> reviews = new HashSet<>();

    public Book() {
    }

    public Book(long id, String title, Timestamp yearPublished, String description, String cover) {
        this.id = id;
        this.title = title;
        this.datePublished = yearPublished;
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Timestamp getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Timestamp date_published) {
        this.datePublished = date_published;
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

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title +
                ", desc=" + description + ", date_published=" + datePublished +
                ", cover =" + cover + ", rating=" + rating + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return id == book.id && Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}


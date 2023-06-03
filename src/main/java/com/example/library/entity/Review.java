package com.example.library.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "rating")
    private int rating;

    @Column(name = "content")
    private String content;

    @Column(name = "timeadded")
    private Date timeAdded;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_idbook", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected Book book;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    protected Set<Comment> comments = new HashSet<>();


    public Review() {
    }

    public Review(int rating, String content, Date timeAdded, Book book) {
        this.rating = rating;
        this.content = content;
        this.timeAdded = timeAdded;
        this.book = book;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Date timeAdded) {
        this.timeAdded = timeAdded;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", rating=" + rating +
                ", content='" + content + '\'' +
                ", timeAdded=" + timeAdded +
                ", book=" + book +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        Review review = (Review) o;
        return id == review.id && rating == review.rating && Objects.equals(content, review.content) && Objects.equals(timeAdded, review.timeAdded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rating, content, timeAdded);
    }

}

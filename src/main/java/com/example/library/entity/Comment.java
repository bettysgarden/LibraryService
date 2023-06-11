package com.example.library.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "comment")

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "content")
    private String content;

    @Column(name = "timeadded")
    private Timestamp timeAdded;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "review_idreview", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected Review review;

    public Comment() {
    }

    public Comment(long id, String content, Timestamp timeAdded) {
        this.id = id;
        this.content = content;
        this.timeAdded = timeAdded;
    }

    public Comment(String content, Timestamp timeAdded) {
        this.content = content;
        this.timeAdded = timeAdded;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Timestamp timeAdded) {
        this.timeAdded = timeAdded;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", timeAdded=" + timeAdded +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return id == comment.id && Objects.equals(content, comment.content) && Objects.equals(timeAdded, comment.timeAdded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, timeAdded);
    }

}

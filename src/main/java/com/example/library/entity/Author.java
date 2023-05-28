package com.example.library.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "author")
public class Author {
    @Id
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")

    private String surname;
    @Column(name = "website")

    private String website;
    @Column(name = "description")

    private String description;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "author_has_book",
            joinColumns = {@JoinColumn(name = "author_idauthor")},
            inverseJoinColumns = {@JoinColumn(name = "book_idbook")})
    private Set<Book> booksSet = new HashSet<>();

    public Author() {
    }

    public Author(String name, String surname, String website, String description) {
        this.name = name;
        this.surname = surname;
        this.website = website;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", surname=" + surname + ", name=" + name +
                ", desc=" + description + ", website=" + website + "]";
    }
}

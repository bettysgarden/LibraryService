package com.example.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

// @Setter // TODO проверить что точно не нужно
// @Getter
// @RequiredArgsConstructor
// @Builder // generates builder class -- seems excessive

@Entity
@Table(name = "book")
@Data // supposed to generate @ToString, @EqualsAndHashCode, @Getter @Setter и @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "rating", columnDefinition = "integer default 0")
    private Double rating;

    @Column(name = "description", length = 10000)
    private String description;

    @Column(name = "cover_url")
    private String coverUrl;

    // the non-owning (inverse side)
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

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();
}

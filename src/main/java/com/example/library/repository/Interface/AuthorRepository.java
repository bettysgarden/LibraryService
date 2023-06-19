package com.example.library.repository.Interface;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.entity.Comment;
import com.example.library.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}

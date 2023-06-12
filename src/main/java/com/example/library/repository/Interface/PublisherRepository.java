package com.example.library.repository.Interface;

import com.example.library.entity.Publisher;
import com.example.library.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
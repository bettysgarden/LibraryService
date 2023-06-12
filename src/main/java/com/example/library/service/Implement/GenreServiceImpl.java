package com.example.library.service.Implement;

import com.example.library.entity.Genre;
import com.example.library.repository.Interface.GenreRepository;
import com.example.library.service.Interface.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class GenreServiceImpl implements GenreService {
    private static final Logger logger = LoggerFactory.getLogger(GenreServiceImpl.class);

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Optional<Genre> findById(long id) {
        logger.debug("inside findById() method");
        return genreRepository.findById(id);
    }

    @Override
    public List<Genre> getAll() {
        logger.debug("inside getAll() method");
        return genreRepository.findAll();
    }

    @Override
    public Genre save(Genre genre) {
        logger.debug("inside save() method");
        genreRepository.save(genre);
        return genre;
    }

    @Override
    public void deleteById(long id) {
        logger.debug("inside delete() method");
        genreRepository.deleteById(id);
    }

}

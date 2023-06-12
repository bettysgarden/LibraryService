package com.example.library.service.Implement;


import com.example.library.entity.Publisher;
import com.example.library.repository.Interface.PublisherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService implements com.example.library.service.Interface.PublisherService {
    private static final Logger logger = LoggerFactory.getLogger(PublisherService.class);

    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public void setPublisherRepository(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Optional<Publisher> findById(long id) {
        logger.debug("inside findById() method");
        try {
            return publisherRepository.findById(id);
        } catch (Exception e) {
            logger.error("Error occurred while finding publisher by ID: {}", id, e);
            throw e;
        }
    }

    @Override
    public List<Publisher> getAll() {
        logger.debug("inside getAll() method");
        try {
            return publisherRepository.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all publishers", e);
            throw e;
        }
    }

    @Override
    public Publisher save(Publisher publisher) {
        logger.debug("inside save() method");
        try {
            return publisherRepository.save(publisher);
        } catch (Exception e) {
            logger.error("Error occurred while saving publisher: {}", publisher, e);
            throw e;
        }
    }

    @Override
    public void deleteById(long id) {
        logger.debug("inside delete() method");
        try {
            publisherRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error occurred while deleting publisher by ID: {}", id, e);
            throw e;
        }
    }
}


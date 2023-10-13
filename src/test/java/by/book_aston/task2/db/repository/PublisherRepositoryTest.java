package by.book_aston.task2.db.repository;

import by.book_aston.task2.config.SpringConfig;
import by.book_aston.task2.db.AbstractTestContainer;
import by.book_aston.task2.model.entity.Publisher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {SpringConfig.class,
        PublisherRepository.class})
class PublisherRepositoryTest  extends AbstractTestContainer {

    private final PublisherRepository publisherRepository;

    @Autowired
    PublisherRepositoryTest(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Test
    void read(){
        Publisher referenceById = publisherRepository.getReferenceById(1L);

        assertEquals(referenceById.getId(),1L);
    }

    @Test
    void create(){
        publisherRepository.save(new Publisher(30L,"Name", new ArrayList<>()));
        assertNotNull(publisherRepository.getReferenceById(30L));
    }

    @Test
    void delete(){
        publisherRepository.deleteById(5L);
        Optional<Publisher> byId = publisherRepository.findById(5L);
        Assertions.assertFalse(byId.isPresent());
    }
}
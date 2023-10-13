package by.book_aston.task2.db.repository;

import by.book_aston.task2.config.SpringConfig;
import by.book_aston.task2.db.AbstractTestContainer;
import by.book_aston.task2.model.entity.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig(classes = {SpringConfig.class,
        AuthorRepository.class})
class AuthorRepositoryTest extends AbstractTestContainer {

    private final AuthorRepository authorRepository;

    @Autowired
    AuthorRepositoryTest(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Test
    void read(){
        Author referenceById = authorRepository.getReferenceById(1L);

        assertEquals(referenceById.getId(),1L);
    }

    @Test
    void create(){
        authorRepository.save(new Author(30L,"Name","Surname"));
        assertNotNull(authorRepository.getReferenceById(30L));
    }

    @Test
    void delete(){
        authorRepository.deleteById(5L);
        Optional<Author> byId = authorRepository.findById(5L);
        Assertions.assertFalse(byId.isPresent());
    }
}
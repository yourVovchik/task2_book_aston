package by.book_aston.task2.db.repository;

import by.book_aston.task2.config.SpringConfig;
import by.book_aston.task2.db.AbstractTestContainer;
import by.book_aston.task2.model.entity.Author;
import by.book_aston.task2.model.entity.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {SpringConfig.class,
        BookRepository.class})
class BookRepositoryTest extends AbstractTestContainer {

    private final BookRepository bookRepository;

    @Autowired
    BookRepositoryTest(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Test
    void read(){
        Book referenceById = bookRepository.getReferenceById(1L);

        assertEquals(referenceById.getId(),1L);
    }

    @Test
    void create(){
        bookRepository.save(new Book(30L,"Name", LocalDate.now()));
        assertNotNull(bookRepository.getReferenceById(30L));
    }

    @Test
    void delete(){
        bookRepository.deleteById(5L);
        Optional<Book> byId = bookRepository.findById(5L);
        Assertions.assertFalse(byId.isPresent());
    }
}
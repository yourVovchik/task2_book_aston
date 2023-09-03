package by.book_aston.task2.db.postgresImp;

import by.book_aston.task2.db.BookDao;
import by.book_aston.task2.model.entity.Author;
import by.book_aston.task2.model.entity.Book;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Testcontainers
class PgBookDaoTest {

    @Container
    private static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14.7")
            .withInitScript("book_init.sql");

    private BookDao bookDao;

    @BeforeEach
    void runContainer(){
        Connection connection;
        try {
            connection = DriverManager.getConnection(container.getJdbcUrl(), container.getUsername(), container.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        bookDao = new PgBookDao(connection);
        container.start();
    }

    @Test
    void get() {
        Book book = bookDao.get(4);
        assertEquals(4,book.getId());
    }

    @Test
    void add() {
        Book book = new Book(13,"Book_TEST", LocalDate.now(),new ArrayList<>());
        long id = bookDao.add(book);
        assertEquals(13,id);
    }

    @Test
    void delete() {
        bookDao.delete(2);
        assertFalse(bookDao.containsId(2));
    }

    @Test
    void editName() {
        String editName = "EDIT_NAME";
        bookDao.editName(5,editName);
        assertEquals(bookDao.get(5).getName(),editName);
    }

    @Test
    void editAuthors() {
        Author author = new Author(1,"Alex222","Adams222");
        bookDao.editAuthors(10, Arrays.asList(author));
        assertEquals(1,bookDao.get(10).getAuthors().get(0).getId());
    }

    @Test
    void containsId() {
        Assert.assertTrue(bookDao.containsId(3));
    }
}
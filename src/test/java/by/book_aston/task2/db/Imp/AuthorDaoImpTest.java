package by.book_aston.task2.db.Imp;

import by.book_aston.task2.db.AuthorDao;
import by.book_aston.task2.model.entity.Author;
import by.book_aston.task2.model.entity.Book;
import by.book_aston.task2.model.entity.Publisher;
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

@Testcontainers
class AuthorDaoImpTest {

    @Container
    private static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14.7")
            .withInitScript("author_init.sql");

    private AuthorDaoImp authorDao;

    @BeforeEach
    void runContainer(){
        Connection connection;
        try {
            connection = DriverManager.getConnection(container.getJdbcUrl(), container.getUsername(), container.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        authorDao = new AuthorDaoImp(connection);
        container.start();
    }

    @Test
    void get() {
        Author author = authorDao.get(4);
        Assert.assertEquals(4,author.getId());
        Assert.assertEquals("Maksim4",author.getName());
        Assert.assertEquals("Tankov4",author.getSurname());
    }

    @Test
    void add() {
        Author author = new Author(0, "Alex", "AlexSurname",new Publisher(1,"TEST",new ArrayList<>()),new ArrayList<>());
        long id = authorDao.add(author);
        Assert.assertEquals(7,id);
    }

    @Test
    void delete() {
        authorDao.delete(1);
        Assert.assertFalse(authorDao.containsId(1));
    }

    @Test
    void editName() {
        String name = "NAME_TEST";
        authorDao.editName(2,name);
        Assert.assertEquals(name,authorDao.get(2).getName());
    }

    @Test
    void editSurname() {
        String name = "SURNAME_TEST";
        authorDao.editSurname(2,name);
        Assert.assertEquals(name,authorDao.get(2).getSurname());
    }

    @Test
    void containsId() {
        Assert.assertTrue(authorDao.containsId(3));
    }

    @Test
    void editBooks() {
        Book book = new Book(3,"New Book", LocalDate.now());
        authorDao.editBooks(5, Arrays.asList(book));
        assertEquals(3,authorDao.get(5).getBookList().get(0).getId());
    }
}
package by.book_aston.task2.db.postgres;

import by.book_aston.task2.db.AuthorDao;
import by.book_aston.task2.model.entity.Author;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Testcontainers
class PgAuthorDaoTest {

    @Container
    private static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14.7")
            .withInitScript("author_init.sql");

    private AuthorDao authorDao;

    @BeforeEach
    void runContainer(){
        Connection connection;
        try {
            connection = DriverManager.getConnection(container.getJdbcUrl(), container.getUsername(), container.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        authorDao = new PgAuthorDao(connection);
        container.start();
    }



    @Test
    void get() {
        Author author = authorDao.get(2);
        Assert.assertEquals(2,author.getId());
    }

    @Test
    void add() {
        Author author = new Author(100, "Alex", "Alex");
        long id = authorDao.add(author);
        Assert.assertEquals(6,id);
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
}
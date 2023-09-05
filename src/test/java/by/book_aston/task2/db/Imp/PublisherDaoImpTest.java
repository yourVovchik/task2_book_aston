package by.book_aston.task2.db.Imp;

import by.book_aston.task2.db.PublisherDao;
import by.book_aston.task2.model.entity.Author;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class PublisherDaoImpTest {

    @Container
    private static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14.7")
            .withInitScript("author_init.sql");

    private PublisherDao publisherDao;

    @BeforeEach
    void runContainer(){
        Connection connection;
        try {
            connection = DriverManager.getConnection(container.getJdbcUrl(), container.getUsername(), container.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        publisherDao = new PublisherDaoImp(connection);
        container.start();
    }

    @Test
    void get() {
        Publisher publisher = publisherDao.get(2);
        assertEquals(2,publisher.getId());
    }

    @Test
    void add() {
        Publisher publisher = new Publisher(4,"MinskNew",new ArrayList<>());
        long id = publisherDao.add(publisher);
        assertEquals(4,id);
    }

    @Test
    void delete() {
        publisherDao.delete(1);
        Assert.assertFalse(publisherDao.containsId(1));
    }

    @Test
    void editName() {
        String name = "NAME_TEST";
        publisherDao.editName(2,name);
        Assert.assertEquals(name,publisherDao.get(2).getName());
    }

    @Test
    void editAuthors() {
        Author author = new Author(20,"Alex222","Adams222");
        publisherDao.editAuthors(2, List.of(author));
        assertEquals(20,publisherDao.get(2).getAuthorList().get(0).getId());

    }

    @Test
    void containsId() {
        assertTrue(publisherDao.containsId(3));
    }
}
package by.book_aston.task2.mapper;

import by.book_aston.task2.model.dto.book.AuthorBooksDto;
import by.book_aston.task2.model.dto.publisher.PublisherDto;
import by.book_aston.task2.model.entity.Author;
import by.book_aston.task2.model.entity.Publisher;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PublisherMapperTest {

    long id = 100;
    String name = "TEST_NAME";

    Author author = new Author(id,name,name);
    List<Author> authorList = List.of(author);


    @Test
    void toPublisherDtoFromPublisher() {
        Publisher publisher = new Publisher(id,name,authorList);
        PublisherDto publisherDto = PublisherMapper.toPublisherDtoFromPublisher(publisher);
        assertEquals(id,publisherDto.getId());
        assertEquals(name,publisherDto.getName());
    }

    @Test
    void toPublisherFromPublisherDto() {
        PublisherDto publisherDto = new PublisherDto(id,name,new ArrayList<>());
        Publisher publisher = PublisherMapper.toPublisherFromPublisherDto(publisherDto);
        assertEquals(id,publisher.getId());
        assertEquals(name,publisher.getName());
    }

    @Test
    void toAuthorPublishersDtoListFromAuthorList() {
        AuthorBooksDto authorBooksDto = BookMapper.toAuthorBooksDtoListFromAuthorList(authorList).get(0);
        assertEquals(id,authorBooksDto.getId());
        assertEquals(name,authorBooksDto.getName());
        assertEquals(name,authorBooksDto.getSurname());

    }

    @Test
    void toAuthorPublishersDtoFromAuthor() {
        AuthorBooksDto authorBooksDto = BookMapper.toAuthorBooksDtoListFromAuthorList(authorList).get(0);
        assertEquals(id,authorBooksDto.getId());
        assertEquals(name,authorBooksDto.getName());
        assertEquals(name,authorBooksDto.getSurname());
    }


    @Test
    void toPublisherFromResultSet() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong("id")).thenReturn(id);
        when(resultSet.getString("name")).thenReturn(name);
        Publisher publisherFromResultSet = PublisherMapper.toPublisherFromResultSet(resultSet);
        assertEquals(id,publisherFromResultSet.getId());
        assertEquals(name,publisherFromResultSet.getName());
    }

    @Test
    void toAuthorFromResultSet() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong("author_id")).thenReturn(id);
        when(resultSet.getString("author_name")).thenReturn(name);
        when(resultSet.getString("author_surname")).thenReturn(name);
        Author authorFromResultSet = PublisherMapper.toAuthorFromResultSet(resultSet);
        assertEquals(id,authorFromResultSet.getId());
        assertEquals(name,authorFromResultSet.getName());
        assertEquals(name,authorFromResultSet.getSurname());
    }
}
package by.book_aston.task2.mapper;

import by.book_aston.task2.model.dto.author.AuthorDto;
import by.book_aston.task2.model.dto.author.BookAuthorsDto;
import by.book_aston.task2.model.entity.Author;
import by.book_aston.task2.model.entity.Book;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthorMapperTest {

    long id = 100;
    String name = "TEST_NAME";
    String surname = "TEST_SURNAME";


    @Test
    void toAuthorDtoFromAuthor() {
        Author author = new Author(id,name,surname);
        AuthorDto authorDto = AuthorMapper.toAuthorDtoFromAuthor(author);
        assertEquals(id,authorDto.getId());
        assertEquals(name,authorDto.getName());
        assertEquals(surname,authorDto.getSurname());
    }

    @Test
    void toBookAuthorsDtoListFromBookList() {
        long id = 1;
        String name = "TEST_BOOK";
        LocalDate localDate = LocalDate.now();
        Book book = new Book(id,name,localDate);
        BookAuthorsDto bookAuthorsDto = AuthorMapper.toBookAuthorsDtoListFromBookList(List.of(book)).get(0);
        assertEquals(id,bookAuthorsDto.getId());
        assertEquals(name,bookAuthorsDto.getName());
        assertEquals(localDate,bookAuthorsDto.getPublicationDate());
    }

    @Test
    void toAuthorFromDto() {
        AuthorDto authorDto = new AuthorDto(id,name,surname,new ArrayList<>());
        Author authorFromDto = AuthorMapper.toAuthorFromDto(authorDto);
        assertEquals(id,authorFromDto.getId());
        assertEquals(name,authorFromDto.getName());
        assertEquals(surname,authorFromDto.getSurname());
    }

    @Test
    void toBookAuthorsDtoFromBook() {
        long id = 1;
        String name = "TEST_BOOK";
        LocalDate localDate = LocalDate.now();
        Book book = new Book(id,name,localDate);
        BookAuthorsDto bookAuthorsDto = AuthorMapper.toBookAuthorsDtoFromBook(book);
        assertEquals(id,bookAuthorsDto.getId());
        assertEquals(name,bookAuthorsDto.getName());
        assertEquals(localDate,bookAuthorsDto.getPublicationDate());
    }

    @Test
    void toAuthorFromResultSet() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong("id")).thenReturn(id);
        when(resultSet.getString("name")).thenReturn(name);
        when(resultSet.getString("surname")).thenReturn(surname);
        Author authorFromResultSet = AuthorMapper.toAuthorFromResultSet(resultSet);
        assertEquals(id,authorFromResultSet.getId());
        assertEquals(name,authorFromResultSet.getName());
        assertEquals(surname,authorFromResultSet.getSurname());

    }


    @Test
    void toBookFromResultSet() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong("book_id")).thenReturn(id);
        when(resultSet.getString("book_name")).thenReturn(name);
        LocalDate localDate = LocalDate.now();
        when(resultSet.getString("book_publication_date")).thenReturn(String.valueOf(localDate));
        Book bookFromResultSet = AuthorMapper.toBookFromResultSet(resultSet);
        assertEquals(id,bookFromResultSet.getId());
        assertEquals(name,bookFromResultSet.getName());
        assertEquals(localDate,bookFromResultSet.getPublicationDate());
    }
}
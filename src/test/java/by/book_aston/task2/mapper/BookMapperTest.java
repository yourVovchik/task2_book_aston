package by.book_aston.task2.mapper;

import by.book_aston.task2.model.dto.book.AuthorBooksDto;
import by.book_aston.task2.model.dto.book.BookDto;
import by.book_aston.task2.model.entity.Author;
import by.book_aston.task2.model.entity.Book;
import by.book_aston.task2.model.entity.Publisher;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookMapperTest {

    long id = 100;
    String name = "TEST_NAME";
    LocalDate localDate = LocalDate.now();


    @Test
    void toBookFromBookDto() {
        BookDto bookDto = new BookDto(id,name, localDate,new ArrayList<>());
        Book book = BookMapper.toBookFromBookDto(bookDto);
        assertEquals(id,book.getId());
        assertEquals(name,book.getName());
        assertEquals(localDate,book.getPublicationDate());
    }

    @Test
    void toBookDtoFromBook() {
        Book book = new Book(id,name,localDate, new ArrayList<>());
        BookDto bookDtoFromBook = BookMapper.toBookDtoFromBook(book);
        assertEquals(id,bookDtoFromBook.getId());
        assertEquals(name,bookDtoFromBook.getName());
        assertEquals(localDate,bookDtoFromBook.getPublicationDate());
    }

    @Test
    void toAuthorBooksDtoListFromAuthorList() {
        Author author = new Author(id,name,name,null,null);
        AuthorBooksDto authorBooksDto = BookMapper.toAuthorBooksDtoListFromAuthorList(List.of(author)).get(0);
        assertEquals(id,authorBooksDto.getId());
        assertEquals(name,authorBooksDto.getName());
        assertEquals(name,authorBooksDto.getSurname());
    }

    @Test
    void toBookFromResultSet() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong("id")).thenReturn(id);
        when(resultSet.getString("name")).thenReturn(name);
        when(resultSet.getString("publication_date")).thenReturn(String.valueOf(localDate));
        Book bookFromResultSet = BookMapper.toBookFromResultSet(resultSet);
        assertEquals(id,bookFromResultSet.getId());
        assertEquals(name,bookFromResultSet.getName());
        assertEquals(localDate,bookFromResultSet.getPublicationDate());
    }
}
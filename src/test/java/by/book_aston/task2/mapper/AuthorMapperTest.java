package by.book_aston.task2.mapper;

import by.book_aston.task2.model.dto.author.AuthorDto;
import by.book_aston.task2.model.dto.author.BookAuthorsDto;
import by.book_aston.task2.model.entity.Author;
import by.book_aston.task2.model.entity.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

}
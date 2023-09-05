package by.book_aston.task2.service;

import by.book_aston.task2.db.BookDao;
import by.book_aston.task2.model.dto.book.BookDto;
import by.book_aston.task2.model.entity.Author;
import by.book_aston.task2.model.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class BookServiceTest {

    private BookService bookService;
    private BookDao bookDao;

    @BeforeEach
    void init(){
        bookDao = Mockito.mock(BookDao.class);
        bookService = new BookService(bookDao);
    }

    @Test
    void get() {
        long id = 10;
        Book book = new Book(10,"Book", LocalDate.now(),new ArrayList<>());
        Mockito.when(bookDao.containsId(id)).thenReturn(true);
        Mockito.when(bookDao.get(id)).thenReturn(book);
        BookDto bookDto = bookService.get(id);
        assertEquals(book.getId(), bookDto.getId());
        assertEquals(book.getName(), bookDto.getName());
        assertEquals(book.getPublicationDate(), bookDto.getPublicationDate());
        assertEquals(book.getAuthors(), bookDto.getAuthors());
    }

    @Test
    void add() {
        BookDto bookDto = new BookDto(1,"Alex",LocalDate.now(),new ArrayList<>());
        Mockito.when(bookDao.add(any())).thenReturn(100L);
        long add = bookService.add(bookDto);
        assertEquals(100,add);
    }

    @Test
    void delete() {
        long id = 10;
        Mockito.when(bookDao.containsId(id)).thenReturn(true);
        bookService.delete(id);
        verify(bookDao, times(1)).delete(id);
    }

    @Test
    void editName() {
        long id = 10;
        String name = "Book";
        Mockito.when(bookDao.containsId(id)).thenReturn(true);
        bookService.editName(id,name);
        verify(bookDao, times(1)).editName(id,name);
    }

    @Test
    void editAuthors() {
        long id = 10;
        List<Author> authorList = new ArrayList<>();
        Mockito.when(bookDao.containsId(id)).thenReturn(true);
        bookService.editAuthors(id,authorList);
        verify(bookDao, times(1)).editAuthors(id,authorList);
    }

    @Test
    void containsId() {
        long id = 10;
        Mockito.when(bookDao.containsId(id)).thenReturn(true);
        assertTrue(bookService.containsId(id));
    }
}
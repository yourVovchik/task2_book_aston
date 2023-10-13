package by.book_aston.task2.service.springJpa;

import by.book_aston.task2.db.repository.BookRepository;
import by.book_aston.task2.mapper.mapstruct.BookMapper;
import by.book_aston.task2.model.dto.book.BookDto;
import by.book_aston.task2.model.entity.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookSpringJpaServiceTest {

    @InjectMocks
    BookSpringJpaService bookSpringJpaService;
    @Mock
    BookRepository bookRepository;
    @Mock
    BookMapper bookMapper;

    @Test
    void get() {
        Book book = new Book();

        when(bookRepository.findById(any(Long.class))).thenReturn(Optional.of(book));
        when(bookMapper.toBookDto(any(Book.class))).thenReturn(new BookDto());

        bookSpringJpaService.get(1L);

        verify(bookRepository).findById(1L);
        verify(bookMapper).toBookDto(book);
    }

    @Test
    void add() {
        Book book = new Book();

        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toBook(any(BookDto.class))).thenReturn(book);

        bookSpringJpaService.add(new BookDto());

        verify(bookRepository).save(book);
        verify(bookMapper).toBook(any(BookDto.class));
    }

    @Test
    void delete() {
        bookSpringJpaService.delete(1L);

        verify(bookRepository).deleteById(1L);
    }

    @Test
    void editName() {
        Book book = new Book();

        when(bookRepository.getReferenceById(any(Long.class))).thenReturn(book);

        bookSpringJpaService.editName(1L,"name");

        verify(bookRepository).getReferenceById(any(Long.class));
        verify(bookRepository).save(book);
    }

    @Test
    void editAuthors() {
        Book book = new Book();

        when(bookRepository.getReferenceById(any(Long.class))).thenReturn(book);

        bookSpringJpaService.editAuthors(1L,new ArrayList<>());

        verify(bookRepository).getReferenceById(any(Long.class));
        verify(bookRepository).save(book);
    }

    @Test
    void containsId() {
        when(bookRepository.findById(any(Long.class))).thenReturn(Optional.of(new Book()));

        boolean bookContains = bookSpringJpaService.containsId(10L);

        assert(bookContains);
        verify(bookRepository).findById(any(Long.class));
    }
}
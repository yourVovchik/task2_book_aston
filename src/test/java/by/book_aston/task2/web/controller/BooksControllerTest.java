package by.book_aston.task2.web.controller;

import by.book_aston.task2.model.dto.book.BookDto;
import by.book_aston.task2.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class BooksControllerTest {

    @Mock
    private BookService bookService;

    @Mock
    private Model model;

    @InjectMocks
    private BooksController booksController;

    @Test
    void get() {
        BookDto bookDto = new BookDto(10L,"Name", LocalDate.now(),new ArrayList<>());

        when(bookService.get(10L)).thenReturn(bookDto);

        booksController.get(10L, model);

        verify(bookService).get((any(Long.class)));
        verify(model).addAttribute(any(String.class) ,eq(bookDto));
    }

    @Test
    void save() {
        BookDto bookDto = new BookDto(10L,"Name", LocalDate.now(),new ArrayList<>());

        when(model.getAttribute("book")).thenReturn(bookDto);
        when(bookService.add(bookDto)).thenReturn(10L);

        booksController.save(model);

        verify(model).getAttribute("book");
        verify(bookService).add(bookDto);
    }

    @Test
    void editName() {
        String bookName = "BookNew";

        when(model.getAttribute("name")).thenReturn(bookName);

        booksController.editName(10L,model);

        verify(bookService).editName(10L,bookName);
    }
}
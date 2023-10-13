package by.book_aston.task2.web.controller;

import by.book_aston.task2.model.dto.author.AuthorDto;
import by.book_aston.task2.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorControllerTest {

    @Mock
    private AuthorService authorService;

    @Mock
    private Model model;

    @InjectMocks
    private AuthorController authorController;


    @Test
    void get() {
        AuthorDto authorDto = new AuthorDto(10L, "Name", "Surname", new ArrayList<>());

        when(authorService.get(1L)).thenReturn(authorDto);

        authorController.get(1L, model);

        verify(authorService).get((any(Long.class)));
        verify(model).addAttribute(any(String.class) ,eq(authorDto));
    }

    @Test
    void save() {
        AuthorDto authorDto = new AuthorDto(10L, "Name", "Surname", new ArrayList<>());

        when(model.getAttribute("author")).thenReturn(authorDto);
        when(authorService.add(authorDto)).thenReturn(10L);

        authorController.save(model);

        verify(model).getAttribute("author");
        verify(authorService).add(authorDto);
    }

    @Test
    void editName() {
        String authorNew = "AuthorNew";

        when(model.getAttribute("name")).thenReturn(authorNew);

        authorController.editName(10L,model);

        verify(authorService).editName(10L,authorNew);
    }
}
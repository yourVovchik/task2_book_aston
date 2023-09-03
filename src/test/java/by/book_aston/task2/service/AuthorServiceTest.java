package by.book_aston.task2.service;

import by.book_aston.task2.db.AuthorDao;
import by.book_aston.task2.model.dto.AuthorDto;
import by.book_aston.task2.model.entity.Author;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class AuthorServiceTest {

    private AuthorService authorService;
    private AuthorDao authorDao;

    @BeforeEach
    void init(){
        authorDao = Mockito.mock(AuthorDao.class);
        authorService = new AuthorService(authorDao);
    }

    @Test
    void add() {
        AuthorDto authorDto = new AuthorDto(1,"Alex","Alex");
        Mockito.when(authorDao.add(any())).thenReturn(100L);
        long add = authorService.add(authorDto);
        Assert.assertEquals(100,add);
    }

    @Test
    void get() {
        long id = 10;
        Author author = new Author(10,"Alex","Alex");
        Mockito.when(authorDao.containsId(id)).thenReturn(true);
        Mockito.when(authorDao.get(id)).thenReturn(author);
        Assert.assertEquals(id,authorService.get(id).getId());
    }

    @Test
    void delete() {
        long id = 10;
        Mockito.when(authorDao.containsId(id)).thenReturn(true);
        authorService.delete(id);
        verify(authorDao, times(1)).delete(id);
    }

    @Test
    void editName() {
        long id = 10;
        String name = "name";
        Mockito.when(authorDao.containsId(id)).thenReturn(true);
        authorService.editName(id,name);
        verify(authorDao, times(1)).editName(id,name);
    }

    @Test
    void editSurname() {
        long id = 10;
        String surname = "surname";
        Mockito.when(authorDao.containsId(id)).thenReturn(true);
        authorService.editSurname(id,surname);
        verify(authorDao, times(1)).editSurname(id,surname);
    }

    @Test
    void containsId() {
        long id = 10;
        Mockito.when(authorDao.containsId(id)).thenReturn(true);
        assertTrue(authorService.containsId(id));
    }

}
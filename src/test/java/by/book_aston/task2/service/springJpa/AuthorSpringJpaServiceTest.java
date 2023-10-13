package by.book_aston.task2.service.springJpa;

import by.book_aston.task2.db.repository.AuthorRepository;
import by.book_aston.task2.mapper.mapstruct.AuthorMapper;
import by.book_aston.task2.model.dto.author.AuthorDto;
import by.book_aston.task2.model.dto.book.BookDto;
import by.book_aston.task2.model.entity.Author;
import by.book_aston.task2.model.entity.Book;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AuthorSpringJpaServiceTest {


    @InjectMocks
    AuthorSpringJpaService authorSpringJpaService;
    @Mock
    AuthorRepository authorRepository;
    @Mock
    AuthorMapper authorMapper;



    @Test
    void get() {
        Author author = new Author();

        when(authorRepository.findById(any(Long.class))).thenReturn(Optional.of(author));
        when(authorMapper.toAuthorDto(any(Author.class))).thenReturn(new AuthorDto());

        authorSpringJpaService.get(1L);

        verify(authorRepository).findById(1L);
        verify(authorMapper).toAuthorDto(author);
    }


    @Test
    void add() {
        Author author = new Author();

        when(authorRepository.save(author)).thenReturn(author);
        when(authorMapper.toAuthor(any(AuthorDto.class))).thenReturn(author);

        authorSpringJpaService.add(new AuthorDto());

        verify(authorRepository).save(author);
        verify(authorMapper).toAuthor(any(AuthorDto.class));
    }

    @Test
    void delete() {
        authorSpringJpaService.delete(1L);

        verify(authorRepository).deleteById(1L);
    }

    @Test
    void editName() {
        Author author = new Author();

        when(authorRepository.getReferenceById(any(Long.class))).thenReturn(author);

        authorSpringJpaService.editName(1L,"name");

        verify(authorRepository).getReferenceById(any(Long.class));
        verify(authorRepository).save(author);
    }

    @Test
    void editSurname() {
        Author author = new Author();

        when(authorRepository.getReferenceById(any(Long.class))).thenReturn(author);

        authorSpringJpaService.editSurname(1L,"Surname");

        verify(authorRepository).getReferenceById(any(Long.class));
        verify(authorRepository).save(author);
    }

    @Test
    void containsId() {
        when(authorRepository.findById(any(Long.class))).thenReturn(Optional.of(new Author()));

        boolean authorContains = authorSpringJpaService.containsId(10L);

        assert(authorContains);
        verify(authorRepository).findById(any(Long.class));
    }
}
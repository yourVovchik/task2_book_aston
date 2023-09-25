package by.book_aston.task2.service;

import by.book_aston.task2.mapper.BookMapper;
import by.book_aston.task2.model.dto.author.AuthorDto;
import by.book_aston.task2.model.dto.book.BookDto;
import by.book_aston.task2.model.entity.Author;

import java.util.List;

public interface BookService {

    BookDto get(long id);

    long add(BookDto bookDto);

    void delete(long id);

    void editName(long id, String name);

    void editAuthors(long id, List<Author> authors);

    boolean containsId(long id);
}

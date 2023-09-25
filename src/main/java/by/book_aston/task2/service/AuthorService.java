package by.book_aston.task2.service;

import by.book_aston.task2.mapper.AuthorMapper;
import by.book_aston.task2.model.dto.author.AuthorDto;

public interface AuthorService {

    AuthorDto get(long id);

    long add(AuthorDto authorDto);
    void delete(long id);
    void editName(long id, String name);
    void editSurname(long id, String surname);
    boolean containsId(long id);
}

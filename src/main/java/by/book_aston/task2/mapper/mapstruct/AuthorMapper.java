package by.book_aston.task2.mapper.mapstruct;

import by.book_aston.task2.model.dto.author.AuthorDto;
import by.book_aston.task2.model.dto.author.BookAuthorsDto;
import by.book_aston.task2.model.entity.Author;
import by.book_aston.task2.model.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDto toAuthorDto(Author author);
    BookAuthorsDto toBookAuthorDto(Book book);

    Author toAuthor(AuthorDto authorDto);
}

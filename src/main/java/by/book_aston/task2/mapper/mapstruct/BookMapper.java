package by.book_aston.task2.mapper.mapstruct;

import by.book_aston.task2.model.dto.book.AuthorBooksDto;
import by.book_aston.task2.model.dto.book.BookDto;
import by.book_aston.task2.model.entity.Author;
import by.book_aston.task2.model.entity.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto toBookDto(Book book);
    AuthorBooksDto toBookAuthorsDto (Author author);

    Book toBook(BookDto bookDto);

    List<Author> toAuthorList(List<AuthorBooksDto> authorBooksDto);

}

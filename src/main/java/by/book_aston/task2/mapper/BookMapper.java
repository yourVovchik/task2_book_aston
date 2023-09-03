package by.book_aston.task2.mapper;

import by.book_aston.task2.model.dto.BookDto;
import by.book_aston.task2.model.entity.Author;
import by.book_aston.task2.model.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookMapper {

    public static Book parseBook(ResultSet resultSet) throws SQLException {
        return new Book(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                LocalDate.parse(resultSet.getString("publication_date")));
    }

    private static Author parseAuthor(ResultSet resultSet) throws SQLException {
        return new Author(
                resultSet.getLong("author_id"),
                resultSet.getString("author_name"),
                resultSet.getString("author_surname"));
    }

    public static List<Book> parseBookListWithAuthors(ResultSet resultSet) throws SQLException {
        Map<Long,Book> bookMap = new HashMap<>();
        while (resultSet.next()){
            long id = resultSet.getLong("id");
            Book book;
            if(!bookMap.containsKey(id)){
                book = parseBook(resultSet);
                book.setAuthors(new ArrayList<>());
            }else{
                book = bookMap.get(id);
            }
            book.getAuthors().add(parseAuthor(resultSet));
            bookMap.put(id,book);
        }
        return new ArrayList<>(bookMap.values());
    }

    public static Book toBookFromDto(BookDto bookDto){
        return new Book(
                bookDto.getId(),
                bookDto.getName(),
                bookDto.getPublicationDate(),
                bookDto.getAuthors()
        );
    }

    public static BookDto fromBooktoBookDto(Book book){
        return new BookDto(
                book.getId(),
                book.getName(),
                book.getPublicationDate(),
                book.getAuthors()
        );
    }
}

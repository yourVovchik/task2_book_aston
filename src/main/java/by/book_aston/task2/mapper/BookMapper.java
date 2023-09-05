package by.book_aston.task2.mapper;

import by.book_aston.task2.model.dto.book.AuthorBooksDto;
import by.book_aston.task2.model.dto.book.BookDto;
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

    public static Book toBookFromResultSet(ResultSet resultSet) throws SQLException {
        return new Book(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                LocalDate.parse(resultSet.getString("publication_date")));
    }

    private static Author toAuthorFromResultSet(ResultSet resultSet) throws SQLException {
        return new Author(
                resultSet.getLong("author_id"),
                resultSet.getString("author_name"),
                resultSet.getString("author_surname"));
    }

    public static List<Book> toBookListFromResultSet(ResultSet resultSet) throws SQLException {
        Map<Long,Book> bookMap = new HashMap<>();
        while (resultSet.next()){
            long id = resultSet.getLong("id");
            Book book;
            if(!bookMap.containsKey(id)){
                book = toBookFromResultSet(resultSet);
                book.setAuthors(new ArrayList<>());
            }else{
                book = bookMap.get(id);
            }
            book.getAuthors().add(toAuthorFromResultSet(resultSet));
            bookMap.put(id,book);
        }
        return new ArrayList<>(bookMap.values());
    }

    public static Book toBookFromBookDto(BookDto bookDto){
        return new Book(
                bookDto.getId(),
                bookDto.getName(),
                bookDto.getPublicationDate(),
                toAuthorListfromBookAuthorDtoList(bookDto.getAuthors())
        );
    }



    public static BookDto toBookDtoFromBook(Book book){
        return new BookDto(
                book.getId(),
                book.getName(),
                book.getPublicationDate(),
                toAuthorBooksDtoListFromAuthorList(book.getAuthors())
        );
    }

    private static List<Author> toAuthorListfromBookAuthorDtoList(List<AuthorBooksDto> authorBooksDtoList){
        List<Author> auhtorList = new ArrayList<>();
        for(AuthorBooksDto authorBooksDto : authorBooksDtoList){
            auhtorList.add(
                    new Author(
                            authorBooksDto.getId(),
                            authorBooksDto.getName(),
                            authorBooksDto.getSurname()));
        }
        return auhtorList;
    }

    public static List<AuthorBooksDto> toAuthorBooksDtoListFromAuthorList(List<Author> authorList){
        List<AuthorBooksDto> authorBooksDtoList = new ArrayList<>();
        for(Author author : authorList){
            authorBooksDtoList.add(
                    new AuthorBooksDto(
                            author.getId(),
                            author.getName(),
                            author.getSurname()));
        }
        return authorBooksDtoList;
    }
}

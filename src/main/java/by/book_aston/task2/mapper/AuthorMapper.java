package by.book_aston.task2.mapper;

import by.book_aston.task2.model.dto.author.BookAuthorsDto;
import by.book_aston.task2.model.dto.author.AuthorDto;
import by.book_aston.task2.model.entity.Author;
import by.book_aston.task2.model.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthorMapper {
    public static Author toAuthorFromResultSet(ResultSet resultSet) throws SQLException {
        return new Author(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("surname"));
    }

    public static List<Author> toAuthorListFromResultSet(ResultSet resultSet) throws SQLException {
        Map<Long,Author> authorMap = new HashMap<>();
        while (resultSet.next()){
            long id = resultSet.getLong("id");
            Author author;
            if(!authorMap.containsKey(id)){
                author = toAuthorFromResultSet(resultSet);
                author.setBookList(new ArrayList<>());
            }else{
                author = authorMap.get(id);
            }
            author.getBookList().add(toBookFromResultSet(resultSet));
            authorMap.put(id,author);
        }
        return new ArrayList<>(authorMap.values());
    }

    public static AuthorDto toAuthorDtoFromAuthor(Author author){
        return new AuthorDto(
                author.getId(),
                author.getName(),
                author.getSurname(),
                toBookAuthorsDtoListFromBookList(author.getBookList())
        );
    }

    public static List<BookAuthorsDto> toBookAuthorsDtoListFromBookList(List<Book> bookList){
        if(bookList == null){
            return new ArrayList<>();
        }
        List<BookAuthorsDto> bookAuthorsDtoList = new ArrayList<>();
        for(Book book : bookList){
            bookAuthorsDtoList.add(
                    toBookAuthorsDtoFromBook(book));
        }
        return bookAuthorsDtoList;
    }

    public static Author toAuthorFromDto(AuthorDto authorDto){
        return new Author(
                authorDto.getId(),
                authorDto.getName(),
                authorDto.getSurname()
        );
    }


    public static BookAuthorsDto toBookAuthorsDtoFromBook(Book book) {
        return new BookAuthorsDto(
                book.getId(),
                book.getName(),
                book.getPublicationDate()
        );
    }

    public static Book toBookFromResultSet(ResultSet resultSet) throws SQLException {
        return new Book(
                resultSet.getLong("book_id"),
                resultSet.getString("book_name"),
                LocalDate.parse(resultSet.getString("book_publication_date")));
    }

}

package by.book_aston.task2.db;

import by.book_aston.task2.model.entity.Author;
import by.book_aston.task2.model.entity.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookDao {
    Book get(long id);

    long add(Book book);
    void delete(long id);
    void editName(long id, String name);
    void editPublicationDate(long id, LocalDate localDate);
    void editAuthors(long id, List<Author> authors);
    boolean containsId(long id);
}

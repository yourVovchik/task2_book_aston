package by.book_aston.task2.db;

import by.book_aston.task2.model.entity.Author;
import by.book_aston.task2.model.entity.Publisher;

import java.util.List;

public interface PublisherDao {

    Publisher get(long id);
    long add(Publisher publisher);
    void delete(long id);
    void editName(long id, String name);
    void editAuthors(long id, List<Author> authorList);
    boolean containsId(long id);


}

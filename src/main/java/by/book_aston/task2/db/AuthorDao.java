package by.book_aston.task2.db;

import by.book_aston.task2.model.entity.Author;

public interface AuthorDao {

    Author get(long id);
    long add(Author author);
    void delete(long id);
    void editName(long id, String name);
    void editSurname(long id, String surname);
    boolean containsId(long id);

}

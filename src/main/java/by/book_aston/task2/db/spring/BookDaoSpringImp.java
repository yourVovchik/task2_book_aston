package by.book_aston.task2.db.spring;

import by.book_aston.task2.db.BookDao;
import by.book_aston.task2.model.entity.Author;
import by.book_aston.task2.model.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
public class BookDaoSpringImp implements BookDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public BookDaoSpringImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public Book get(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class,id);
    }

    @Transactional
    @Override
    public long add(Book book) {
        Session session = sessionFactory.getCurrentSession();
        return (Integer) session.save(book);
    }

    @Transactional
    @Override
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Book.class, id));
    }

    @Transactional
    @Override
    public void editName(long id, String name) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        book.setName(name);
    }

    @Transactional
    @Override
    public void editPublicationDate(long id, LocalDate localDate) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        book.setPublicationDate(localDate);
    }

    @Transactional
    @Override
    public void editAuthors(long id, List<Author> authors) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        book.setAuthors(authors);
    }

    @Transactional
    @Override
    public boolean containsId(long id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        return book != null;
    }
}

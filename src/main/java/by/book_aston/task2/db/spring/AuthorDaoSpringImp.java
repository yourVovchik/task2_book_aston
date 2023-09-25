package by.book_aston.task2.db.spring;

import by.book_aston.task2.db.AuthorDao;
import by.book_aston.task2.model.entity.Author;
import by.book_aston.task2.model.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Component
public class AuthorDaoSpringImp implements AuthorDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public AuthorDaoSpringImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public Author get(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Author.class,id);
    }
    @Transactional
    @Override
    public long add(Author author) {
        Session session = sessionFactory.getCurrentSession();
        return (Integer) session.save(author);
    }
    @Transactional
    @Override
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Author.class, id));
    }

    @Transactional
    @Override
    public void editName(long id, String name) {
        Session session = sessionFactory.getCurrentSession();
        Author author = session.get(Author.class, id);
        author.setName(name);
    }
    @Transactional
    @Override
    public void editSurname(long id, String surname) {
        Session session = sessionFactory.getCurrentSession();
        Author author = session.get(Author.class, id);
        author.setSurname(surname);
    }
    @Transactional
    @Override
    public boolean containsId(long id) {
        Session session = sessionFactory.getCurrentSession();
        Author author = session.get(Author.class, id);
        return author != null;
    }
    @Transactional
    @Override
    public void editBooks(long id, List<Book> books) {
        Session session = sessionFactory.getCurrentSession();
        Author author = session.get(Author.class, id);
        author.setBookList(books);

    }
}

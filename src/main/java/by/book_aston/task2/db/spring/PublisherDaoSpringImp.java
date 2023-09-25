package by.book_aston.task2.db.spring;

import by.book_aston.task2.db.PublisherDao;
import by.book_aston.task2.model.entity.Author;
import by.book_aston.task2.model.entity.Book;
import by.book_aston.task2.model.entity.Publisher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PublisherDaoSpringImp implements PublisherDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public PublisherDaoSpringImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Publisher get(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Publisher.class,id);
    }

    @Override
    public long add(Publisher publisher) {
        Session session = sessionFactory.getCurrentSession();
        return (Integer) session.save(publisher);
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Publisher.class, id));
    }

    @Override
    public void editName(long id, String name) {
        Session session = sessionFactory.getCurrentSession();
        Publisher publisher = session.get(Publisher.class, id);
        publisher.setName(name);
    }

    @Override
    public void editAuthors(long id, List<Author> authorList) {
        Session session = sessionFactory.getCurrentSession();
        Publisher publisher = session.get(Publisher.class, id);
        publisher.setAuthorList(authorList);
    }

    @Override
    public boolean containsId(long id) {
        Session session = sessionFactory.getCurrentSession();
        Publisher publisher = session.get(Publisher.class, id);
        return publisher != null;
    }
}

package by.book_aston.task2.service.spring;

import by.book_aston.task2.db.BookDao;
import by.book_aston.task2.mapper.mapstruct.BookMapper;
import by.book_aston.task2.model.dto.book.BookDto;
import by.book_aston.task2.model.entity.Author;
import by.book_aston.task2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceSpringImp implements BookService {

    private final BookDao bookDao;
    private final BookMapper bookMapper;

    @Autowired
    public BookServiceSpringImp(BookDao bookDao, BookMapper bookMapper) {
        this.bookDao = bookDao;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDto get(long id) {
        return bookMapper.toBookDto(bookDao.get(id));
    }

    @Override
    public long add(BookDto bookDto) {
        return bookDao.add(bookMapper.toBook(bookDto));
    }

    @Override
    public void delete(long id) {
        if(bookDao.containsId(id)){
            bookDao.delete(id);
        }
    }

    @Override
    public void editName(long id, String name) {
        if(bookDao.containsId(id)){
            bookDao.editName(id,name);
        }
    }

    @Override
    public void editAuthors(long id, List<Author> authors) {
        if(bookDao.containsId(id)){
            bookDao.editAuthors(id,authors);
        }
    }

    @Override
    public boolean containsId(long id) {
        return bookDao.containsId(id);
    }
}

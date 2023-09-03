package by.book_aston.task2.service;

import by.book_aston.task2.db.BookDao;
import by.book_aston.task2.mapper.BookMapper;
import by.book_aston.task2.model.dto.BookDto;
import by.book_aston.task2.model.entity.Author;

import java.util.List;

public class BookService {

    private final BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public BookDto get(long id){
        if(bookDao.containsId(id)){
            return BookMapper.fromBooktoBookDto(bookDao.get(id));
        }
        throw new NullPointerException(id + " not found");
    };

    public long add(BookDto bookDto){
        return bookDao.add(BookMapper.toBookFromDto(bookDto));
    };
    public void delete(long id){
        bookDao.delete(id);
    };
    public void editName(long id, String name){
        if(bookDao.containsId(id)){
            bookDao.editName(id,name);
        }else{
            throw new NullPointerException(id + " not found");
        }

    };
    public void editAuthors(long id, List<Author> authors){
        if(bookDao.containsId(id)){
            bookDao.editAuthors(id,authors);
        }else{
            throw new NullPointerException(id + " not found");
        }
    };
    public boolean containsId(long id){
        return bookDao.containsId(id);
    };
}

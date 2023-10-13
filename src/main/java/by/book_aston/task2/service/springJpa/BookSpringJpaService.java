package by.book_aston.task2.service.springJpa;

import by.book_aston.task2.db.repository.BookRepository;
import by.book_aston.task2.mapper.mapstruct.BookMapper;
import by.book_aston.task2.model.dto.book.BookDto;
import by.book_aston.task2.model.entity.Author;
import by.book_aston.task2.model.entity.Book;
import by.book_aston.task2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookSpringJpaService implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookSpringJpaService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDto get(long id) {
        return bookMapper.toBookDto(bookRepository.findById(id).get());
    }

    @Override
    public long add(BookDto bookDto) {
        return bookRepository.save(bookMapper.toBook(bookDto)).getId();
    }

    @Override
    public void delete(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void editName(long id, String name) {
        Book book = bookRepository.getReferenceById(id);
        book.setName(name);
        bookRepository.save(book);
    }

    @Override
    public void editAuthors(long id, List<Author> authors) {
        Book book = bookRepository.getReferenceById(id);
        book.setAuthors(authors);
        bookRepository.save(book);
    }

    @Override
    public boolean containsId(long id) {
        return bookRepository.findById(id).isPresent();
    }
}

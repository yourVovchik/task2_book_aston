package by.book_aston.task2.service.springJpa;

import by.book_aston.task2.db.repository.AuthorRepository;
import by.book_aston.task2.mapper.mapstruct.AuthorMapper;
import by.book_aston.task2.model.dto.author.AuthorDto;
import by.book_aston.task2.model.entity.Author;
import by.book_aston.task2.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthorSpringJpaService implements AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorSpringJpaService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public AuthorDto get(long id) {
        return authorMapper.toAuthorDto(authorRepository.findById(id).get());
    }

    @Override
    public long add(AuthorDto authorDto) {
        return authorRepository.save(authorMapper.toAuthor(authorDto)).getId();
    }

    @Override
    public void delete(long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public void editName(long id, String name) {
        Author author = authorRepository.getReferenceById(id);
        author.setName(name);
        authorRepository.save(author);
    }

    @Override
    public void editSurname(long id, String surname) {
        Author author = authorRepository.getReferenceById(id);
        author.setSurname(surname);
        authorRepository.save(author);
    }

    @Override
    public boolean containsId(long id) {
        return authorRepository.findById(id).isPresent();
    }
}

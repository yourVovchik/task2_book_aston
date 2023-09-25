package by.book_aston.task2.service.spring;

import by.book_aston.task2.db.AuthorDao;
import by.book_aston.task2.mapper.mapstruct.AuthorMapper;
import by.book_aston.task2.model.dto.author.AuthorDto;
import by.book_aston.task2.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceSpringImp implements AuthorService {

    private final AuthorDao authorDao;
    protected final AuthorMapper authorMapper;

    @Autowired
    public AuthorServiceSpringImp(AuthorDao authorDao, AuthorMapper authorMapper) {
        this.authorDao = authorDao;
        this.authorMapper = authorMapper;
    }

    @Override
    public AuthorDto get(long id) {
        return authorMapper.toAuthorDto(authorDao.get(id));
    }

    @Override
    public long add(AuthorDto authorDto) {
        return authorDao.add(authorMapper.toAuthor(authorDto));
    }

    @Override
    public void delete(long id) {
        if(authorDao.containsId(id)){
            authorDao.delete(id);
        }

    }

    @Override
    public void editName(long id, String name) {
        if(authorDao.containsId(id)){
            authorDao.editName(id,name);
        }
    }

    @Override
    public void editSurname(long id, String surname) {
        if(authorDao.containsId(id)){
            authorDao.editSurname(id,surname);
        }
    }

    @Override
    public boolean containsId(long id) {
        return authorDao.containsId(id);
    }
}

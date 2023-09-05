package by.book_aston.task2.service;

import by.book_aston.task2.db.AuthorDao;
import by.book_aston.task2.mapper.AuthorMapper;
import by.book_aston.task2.model.dto.author.AuthorDto;

public class AuthorService {
    private final AuthorDao authorDao;

    public AuthorService(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public long add(AuthorDto authorDto){
        return authorDao.add(AuthorMapper.toAuthorFromDto(authorDto));
    }

    public AuthorDto get(long id){
        if(authorDao.containsId(id)){
            return AuthorMapper.toAuthorDtoFromAuthor(authorDao.get(id));
        }
        throw new NullPointerException(id + " not found");
    }

    public void delete(long id){
        if(authorDao.containsId(id)){
            authorDao.delete(id);
        }else{
            throw new NullPointerException(id + " not found");
        }
    }

    public void editName(long id, String name){
        if(authorDao.containsId(id)){
            authorDao.editName(id,name);
        }else {
            throw new NullPointerException(id + " not found");
        }
    };
    public void editSurname(long id, String surname){
        if(authorDao.containsId(id)){
            authorDao.editSurname(id,surname);
        }else {
            throw new NullPointerException(id + " not found");
        }
    };
    public boolean containsId(long id){
        return authorDao.containsId(id);
    };
}

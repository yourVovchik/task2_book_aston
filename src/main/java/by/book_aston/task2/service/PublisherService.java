package by.book_aston.task2.service;

import by.book_aston.task2.db.PublisherDao;
import by.book_aston.task2.mapper.PublisherMapper;
import by.book_aston.task2.model.dto.publisher.PublisherDto;
import by.book_aston.task2.model.entity.Author;

import java.util.List;

public class PublisherService {

    private final PublisherDao publisherDao;

    public PublisherService(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    public PublisherDto get(long id){
        if(publisherDao.containsId(id)){
            return PublisherMapper.toPublisherDtoFromPublisher(publisherDao.get(id));
        }
        throw new NullPointerException(id + " not found");
    }

    public long add(PublisherDto publisherDto){
        return publisherDao.add(PublisherMapper.toPublisherFromPublisherDto(publisherDto));
    }

    public void delete(long id){
        if(publisherDao.containsId(id)){
            publisherDao.delete(id);
        }else{
            throw new NullPointerException(id + " not found");
        }
    };
    public void editName(long id, String name){
        if(publisherDao.containsId(id)){
            publisherDao.editName(id,name);
        }else{
            throw new NullPointerException(id + " not found");
        }
    };
    void editAuthors(long id, List<Author> authorList){
        if(publisherDao.containsId(id)){
            publisherDao.editAuthors(id,authorList);
        }else{
            throw new NullPointerException(id + " not found");
        }
    };

}

package by.book_aston.task2.service.spring;

import by.book_aston.task2.db.PublisherDao;
import by.book_aston.task2.mapper.mapstruct.PublisherMapper;
import by.book_aston.task2.model.dto.publisher.PublisherDto;
import by.book_aston.task2.model.entity.Author;
import by.book_aston.task2.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceSpringImp implements PublisherService {

    private final PublisherDao publisherDao;

    private final PublisherMapper publisherMapper;

    @Autowired
    public PublisherServiceSpringImp(PublisherDao bookDao, PublisherMapper publisherMapper) {
        this.publisherDao = bookDao;
        this.publisherMapper = publisherMapper;
    }



    @Override
    public PublisherDto get(long id) {
        if(publisherDao.containsId(id)){
            return publisherMapper.publisherDto(publisherDao.get(id));
        }
        throw new NullPointerException();
    }

    @Override
    public long add(PublisherDto publisherDto) {
        return publisherDao.add(publisherMapper.toPublisher(publisherDto));
    }

    @Override
    public void delete(long id) {
        if(publisherDao.containsId(id)){
            publisherDao.delete(id);
        }
    }

    @Override
    public void editName(long id, String name) {
        if(publisherDao.containsId(id)){
            publisherDao.editName(id,name);
        }
    }

    @Override
    public void editAuthors(long id, List<Author> authors) {
        if(publisherDao.containsId(id)){
            publisherDao.editAuthors(id,authors);
        }
    }
}

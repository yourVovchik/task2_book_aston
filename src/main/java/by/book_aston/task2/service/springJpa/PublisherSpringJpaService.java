package by.book_aston.task2.service.springJpa;

import by.book_aston.task2.db.repository.PublisherRepository;
import by.book_aston.task2.mapper.mapstruct.PublisherMapper;
import by.book_aston.task2.model.dto.publisher.PublisherDto;
import by.book_aston.task2.model.entity.Author;
import by.book_aston.task2.model.entity.Publisher;
import by.book_aston.task2.service.PublisherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherSpringJpaService implements PublisherService {

    private final PublisherRepository publisherRepository;

    private final PublisherMapper publisherMapper;

    public PublisherSpringJpaService(PublisherRepository publisherRepository, PublisherMapper publisherMapper) {
        this.publisherRepository = publisherRepository;
        this.publisherMapper = publisherMapper;
    }

    @Override
    public PublisherDto get(long id) {
        return publisherMapper.publisherDto(publisherRepository.findById(id).get());
    }

    @Override
    public long add(PublisherDto publisherDto) {
        return publisherRepository.save(publisherMapper.toPublisher(publisherDto)).getId();
    }

    @Override
    public void delete(long id) {
        publisherRepository.deleteById(id);
    }

    @Override
    public void editName(long id, String name) {
        Publisher publisher = publisherRepository.getReferenceById(id);
        publisher.setName(name);
        publisherRepository.save(publisher);
    }

    @Override
    public void editAuthors(long id, List<Author> authorList) {
        Publisher publisher = publisherRepository.getReferenceById(id);
        publisher.setAuthorList(authorList);
        publisherRepository.save(publisher);
    }
}

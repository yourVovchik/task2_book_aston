package by.book_aston.task2.service;

import by.book_aston.task2.mapper.PublisherMapper;
import by.book_aston.task2.model.dto.publisher.PublisherDto;
import by.book_aston.task2.model.entity.Author;

import java.util.List;

public interface PublisherService {

    PublisherDto get(long id);

    long add(PublisherDto publisherDto);

    void delete(long id);

    void editName(long id, String name);

    void editAuthors(long id, List<Author> authorList);
}

package by.book_aston.task2.mapper.mapstruct;

import by.book_aston.task2.model.dto.publisher.PublisherDto;
import by.book_aston.task2.model.entity.Publisher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

    PublisherDto publisherDto(Publisher publisher);
    Publisher toPublisher(PublisherDto publisherDto);
}

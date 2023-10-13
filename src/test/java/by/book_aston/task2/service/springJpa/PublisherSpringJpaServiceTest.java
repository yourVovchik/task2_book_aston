package by.book_aston.task2.service.springJpa;

import by.book_aston.task2.db.repository.PublisherRepository;
import by.book_aston.task2.mapper.mapstruct.PublisherMapper;
import by.book_aston.task2.model.dto.publisher.PublisherDto;
import by.book_aston.task2.model.entity.Publisher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PublisherSpringJpaServiceTest {

    @InjectMocks
    PublisherSpringJpaService publisherSpringJpaService;
    @Mock
    PublisherRepository publisherRepository;
    @Mock
    PublisherMapper publisherMapper;

    @Test
    void get() {
        Publisher publisher = new Publisher();

        when(publisherRepository.findById(any(Long.class))).thenReturn(Optional.of(publisher));
        when(publisherMapper.publisherDto(any(Publisher.class))).thenReturn(new PublisherDto());

        publisherSpringJpaService.get(1L);

        verify(publisherRepository).findById(1L);
        verify(publisherMapper).publisherDto(publisher);
    }

    @Test
    void add() {
        Publisher publisher = new Publisher();

        when(publisherRepository.save(publisher)).thenReturn(publisher);
        when(publisherMapper.toPublisher(any(PublisherDto.class))).thenReturn(publisher);

        publisherSpringJpaService.add(new PublisherDto());

        verify(publisherRepository).save(publisher);
        verify(publisherMapper).toPublisher(any(PublisherDto.class));
    }

    @Test
    void delete() {
        publisherSpringJpaService.delete(1L);

        verify(publisherRepository).deleteById(1L);
    }

    @Test
    void editName() {
        Publisher publisher = new Publisher();

        when(publisherRepository.getReferenceById(any(Long.class))).thenReturn(publisher);

        publisherSpringJpaService.editName(1L,"name");

        verify(publisherRepository).getReferenceById(any(Long.class));
        verify(publisherRepository).save(publisher);
    }

    @Test
    void editAuthors() {
        Publisher publisher = new Publisher();

        when(publisherRepository.getReferenceById(any(Long.class))).thenReturn(publisher);

        publisherSpringJpaService.editAuthors(1L,new ArrayList<>());

        verify(publisherRepository).getReferenceById(any(Long.class));
        verify(publisherRepository).save(publisher);
    }
}
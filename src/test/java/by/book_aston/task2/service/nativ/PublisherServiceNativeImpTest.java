package by.book_aston.task2.service.nativ;

import by.book_aston.task2.db.PublisherDao;
import by.book_aston.task2.model.dto.publisher.PublisherDto;
import by.book_aston.task2.model.entity.Author;
import by.book_aston.task2.model.entity.Publisher;
import by.book_aston.task2.service.nativ.PublisherServiceNativeImp;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PublisherServiceNativeImpTest {

    private PublisherServiceNativeImp publisherServiceNativeImp;
    private PublisherDao publisherDao;

    @BeforeEach
    void init(){
        publisherDao = Mockito.mock(PublisherDao.class);
        publisherServiceNativeImp = new PublisherServiceNativeImp(publisherDao);
    }

    @Test
    void get() {
        long id = 10;
        Publisher publisher = new Publisher(id,"PUBLISHER_TEST",new ArrayList<>());
        Mockito.when(publisherDao.containsId(id)).thenReturn(true);
        Mockito.when(publisherDao.get(id)).thenReturn(publisher);
        assertEquals(id, publisherServiceNativeImp.get(id).getId());
    }

    @Test
    void add() {
        PublisherDto publisherDto = new PublisherDto(100,"PUBLISHER_TEST",new ArrayList<>());
        Mockito.when(publisherDao.add(any())).thenReturn(100L);
        long add = publisherServiceNativeImp.add(publisherDto);
        Assert.assertEquals(100,add);
    }

    @Test
    void delete() {
        long id = 10;
        Mockito.when(publisherDao.containsId(id)).thenReturn(true);
        publisherServiceNativeImp.delete(id);
        verify(publisherDao, times(1)).delete(id);
    }

    @Test
    void editName() {
        long id = 10;
        String name = "name";
        Mockito.when(publisherDao.containsId(id)).thenReturn(true);
        publisherServiceNativeImp.editName(id,name);
        verify(publisherDao, times(1)).editName(id,name);
    }

    @Test
    void editAuthors() {
        long id = 10;
        List<Author> authorList = new ArrayList<>();
        Mockito.when(publisherDao.containsId(id)).thenReturn(true);
        publisherServiceNativeImp.editAuthors(id,authorList);
        verify(publisherDao, times(1)).editAuthors(id,authorList);
    }
}
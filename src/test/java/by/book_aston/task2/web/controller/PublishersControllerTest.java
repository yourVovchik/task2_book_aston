package by.book_aston.task2.web.controller;

import by.book_aston.task2.model.dto.publisher.PublisherDto;
import by.book_aston.task2.service.PublisherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PublishersControllerTest {

    @Mock
    private PublisherService publisherService;

    @Mock
    private Model model;

    @InjectMocks
    private PublishersController publishersController;

    @Test
    void get() {
        PublisherDto publisherDto = new PublisherDto(10L,"Name", new ArrayList<>());

        when(publisherService.get(10L)).thenReturn(publisherDto);

        publishersController.get(10L, model);

        verify(publisherService).get((any(Long.class)));
        verify(model).addAttribute(any(String.class) ,eq(publisherDto));
    }

    @Test
    void save() {
        PublisherDto publisherDto = new PublisherDto(10L,"Name", new ArrayList<>());

        when(model.getAttribute("publisher")).thenReturn(publisherDto);
        when(publisherService.add(publisherDto)).thenReturn(10L);

        publishersController.save(model);

        verify(model).getAttribute("publisher");
        verify(publisherService).add(publisherDto);
    }

    @Test
    void editName() {
        String publisherName = "PublisherNew";

        when(model.getAttribute("name")).thenReturn(publisherName);

        publishersController.editName(10L,model);

        verify(publisherService).editName(10L,publisherName);
    }
}
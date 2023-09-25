package by.book_aston.task2.web.servlet.publisher;

import by.book_aston.task2.model.dto.publisher.PublisherDto;
import by.book_aston.task2.service.nativ.PublisherServiceNativeImp;
import by.book_aston.task2.web.LocalDateAdapter;
import com.google.gson.GsonBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class PublisherAddServletTest {

    @Test
    void collService() throws ServletException, IOException {

        final PublisherServiceNativeImp service = mock(PublisherServiceNativeImp.class);
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final BufferedReader bufferedReader = mock(BufferedReader.class);
        final PrintWriter printWriter = mock(PrintWriter.class);
        PublisherDto publisherDto = new PublisherDto(1,"NAME",new ArrayList<>());
        String json = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create().toJson(publisherDto);
        when(bufferedReader.readLine()).thenReturn(json);
        PublisherAddServlet servlet = new PublisherAddServlet(service,bufferedReader,printWriter);
        servlet.doPost(request, response);
        verify(service, times(1)).add(any());
    }

}
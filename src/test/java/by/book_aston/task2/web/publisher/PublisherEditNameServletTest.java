package by.book_aston.task2.web.publisher;

import by.book_aston.task2.service.BookService;
import by.book_aston.task2.service.PublisherService;
import by.book_aston.task2.web.book.BookEditNameServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class PublisherEditNameServletTest {

    @Test
    void collService() throws ServletException, IOException {

        final PublisherService service = mock(PublisherService.class);
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);

        PublisherEditNameServlet servlet = new PublisherEditNameServlet(service);
        long id = 10;
        String name = "TEST_NAME";
        when(request.getParameter("id")).thenReturn(String.valueOf(id));
        when(request.getParameter("name")).thenReturn(name);

        servlet.doPost(request, response);

        verify(service, times(1)).editName(id,name);
    }

}
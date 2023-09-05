package by.book_aston.task2.web.author;

import by.book_aston.task2.service.AuthorService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class AuthorEditNameServletTest {


    @Test
    void collService() throws ServletException, IOException {

        final AuthorService service = mock(AuthorService.class);
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);

        AuthorEditNameServlet servlet = new AuthorEditNameServlet(service);
        long id = 10;
        String name = "TEST_NAME";
        when(request.getParameter("id")).thenReturn(String.valueOf(id));
        when(request.getParameter("name")).thenReturn(name);

        servlet.doPost(request, response);

        verify(service, times(1)).editName(id,name);
    }

}
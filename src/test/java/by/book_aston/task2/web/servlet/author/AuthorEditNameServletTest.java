package by.book_aston.task2.web.servlet.author;

import by.book_aston.task2.service.nativ.AuthorServiceNativeImp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class AuthorEditNameServletTest {


    @Test
    void collService() throws ServletException, IOException {

        final AuthorServiceNativeImp service = mock(AuthorServiceNativeImp.class);
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
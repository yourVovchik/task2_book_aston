package by.book_aston.task2.web.servlet.author;

import by.book_aston.task2.service.nativ.AuthorServiceNativeImp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class AuthorEditSurnameServletTest {

    @Test
    void collService() throws ServletException, IOException {

        final AuthorServiceNativeImp service = mock(AuthorServiceNativeImp.class);
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);

        AuthorEditSurnameServlet servlet = new AuthorEditSurnameServlet(service);
        long id = 10;
        String name = "TEST_NAME";
        when(request.getParameter("id")).thenReturn(String.valueOf(id));
        when(request.getParameter("surname")).thenReturn(name);

        servlet.doPost(request, response);

        verify(service, times(1)).editSurname(id,name);
    }

}
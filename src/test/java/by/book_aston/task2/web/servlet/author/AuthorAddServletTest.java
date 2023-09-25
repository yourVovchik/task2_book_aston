package by.book_aston.task2.web.servlet.author;

import by.book_aston.task2.model.dto.author.AuthorDto;
import by.book_aston.task2.model.dto.author.BookAuthorsDto;
import by.book_aston.task2.service.nativ.AuthorServiceNativeImp;
import by.book_aston.task2.web.LocalDateAdapter;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class AuthorAddServletTest {

    @Test
    void collService() throws ServletException, IOException {

        final AuthorServiceNativeImp service = mock(AuthorServiceNativeImp.class);
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final BufferedReader bufferedReader = mock(BufferedReader.class);
        final PrintWriter printWriter = mock(PrintWriter.class);
        BookAuthorsDto bookAuthorsDto = new BookAuthorsDto(1,"name",LocalDate.now());
        AuthorDto author = new AuthorDto(10,"Name","Surname", Arrays.asList(bookAuthorsDto));
        String json = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create().toJson(author);
        when(bufferedReader.readLine()).thenReturn(json);
        AuthorAddServlet servlet = new AuthorAddServlet(service,bufferedReader,printWriter);
        servlet.doPost(request, response);
        verify(service, times(1)).add(any());
    }

}
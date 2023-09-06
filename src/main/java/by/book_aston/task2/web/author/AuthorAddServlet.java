package by.book_aston.task2.web.author;

import by.book_aston.task2.config.ConnectionDB;
import by.book_aston.task2.db.Imp.AuthorDaoImp;
import by.book_aston.task2.model.dto.author.AuthorDto;
import by.book_aston.task2.service.AuthorService;
import by.book_aston.task2.web.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;

@WebServlet(name = "AuthorAdd", urlPatterns = "/author/add")
public class AuthorAddServlet extends HttpServlet {
    private AuthorService authorService;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public AuthorAddServlet(AuthorService authorService, BufferedReader bufferedReader, PrintWriter printWriter) {
        this.authorService = authorService;
        this.bufferedReader = bufferedReader;
        this.printWriter = printWriter;
    }

    public AuthorAddServlet() {
        this.authorService = new AuthorService(new AuthorDaoImp(ConnectionDB.getConnection()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(bufferedReader == null)
            this.bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String json = "";
        json = bufferedReader.readLine();

        AuthorDto authorDto = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create().fromJson(json, AuthorDto.class);

        long add = authorService.add(authorDto);

        if(printWriter == null)
            printWriter = resp.getWriter();

        resp.setContentType("application/json");
        printWriter.print(add);
        printWriter.flush();
    }
}

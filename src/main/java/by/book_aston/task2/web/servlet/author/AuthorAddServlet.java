package by.book_aston.task2.web.servlet.author;

import by.book_aston.task2.config.ConnectionDB;
import by.book_aston.task2.db.Imp.AuthorDaoImp;
import by.book_aston.task2.model.dto.author.AuthorDto;
import by.book_aston.task2.service.nativ.AuthorServiceNativeImp;
import by.book_aston.task2.web.LocalDateAdapter;
import com.google.gson.GsonBuilder;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/author/add")
public class AuthorAddServlet extends HttpServlet {
    private AuthorServiceNativeImp authorServiceNativeImp;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public AuthorAddServlet(AuthorServiceNativeImp authorServiceNativeImp, BufferedReader bufferedReader, PrintWriter printWriter) {
        this.authorServiceNativeImp = authorServiceNativeImp;
        this.bufferedReader = bufferedReader;
        this.printWriter = printWriter;
    }

    public AuthorAddServlet() {
        this.authorServiceNativeImp = new AuthorServiceNativeImp(new AuthorDaoImp(ConnectionDB.getConnection()));
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

        long add = authorServiceNativeImp.add(authorDto);

        if(printWriter == null)
            printWriter = resp.getWriter();

        resp.setContentType("application/json");
        printWriter.print(add);
        printWriter.flush();
    }
}

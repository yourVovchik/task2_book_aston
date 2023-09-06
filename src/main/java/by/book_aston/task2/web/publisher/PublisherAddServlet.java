package by.book_aston.task2.web.publisher;

import by.book_aston.task2.config.ConnectionDB;
import by.book_aston.task2.db.Imp.BookDaoImp;
import by.book_aston.task2.db.Imp.PublisherDaoImp;
import by.book_aston.task2.model.dto.author.AuthorDto;
import by.book_aston.task2.model.dto.publisher.PublisherDto;
import by.book_aston.task2.service.BookService;
import by.book_aston.task2.service.PublisherService;
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

@WebServlet(name = "PublisherAdd", urlPatterns = "/publisher/add")
public class PublisherAddServlet extends HttpServlet {

    private PublisherService publisherService;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public PublisherAddServlet(PublisherService publisherService, BufferedReader bufferedReader, PrintWriter printWriter) {
        this.publisherService = publisherService;
        this.bufferedReader = bufferedReader;
        this.printWriter = printWriter;
    }

    public PublisherAddServlet() {
        this.publisherService = new PublisherService(new PublisherDaoImp(ConnectionDB.getConnection()));
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

        PublisherDto publisherDto = new Gson().fromJson(json, PublisherDto.class);
        long add = publisherService.add(publisherDto);

        if(printWriter == null)
            printWriter = resp.getWriter();

        resp.setContentType("application/json");
        printWriter.print(add);
        printWriter.flush();
    }
}

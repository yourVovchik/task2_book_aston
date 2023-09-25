package by.book_aston.task2.web.servlet.publisher;

import by.book_aston.task2.config.ConnectionDB;
import by.book_aston.task2.db.Imp.PublisherDaoImp;
import by.book_aston.task2.model.dto.author.AuthorDto;
import by.book_aston.task2.model.dto.publisher.PublisherDto;
import by.book_aston.task2.service.nativ.PublisherServiceNativeImp;
import by.book_aston.task2.web.LocalDateAdapter;
import com.google.gson.Gson;
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

@WebServlet(name = "PublisherAdd", urlPatterns = "/publisher/add")
public class PublisherAddServlet extends HttpServlet {

    private PublisherServiceNativeImp publisherServiceNativeImp;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public PublisherAddServlet(PublisherServiceNativeImp publisherServiceNativeImp, BufferedReader bufferedReader, PrintWriter printWriter) {
        this.publisherServiceNativeImp = publisherServiceNativeImp;
        this.bufferedReader = bufferedReader;
        this.printWriter = printWriter;
    }

    public PublisherAddServlet() {
        this.publisherServiceNativeImp = new PublisherServiceNativeImp(new PublisherDaoImp(ConnectionDB.getConnection()));
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
        long add = publisherServiceNativeImp.add(publisherDto);

        if(printWriter == null)
            printWriter = resp.getWriter();

        resp.setContentType("application/json");
        printWriter.print(add);
        printWriter.flush();
    }
}

package by.book_aston.task2.web.publisher;

import by.book_aston.task2.config.ConnectionDB;
import by.book_aston.task2.db.Imp.AuthorDaoImp;
import by.book_aston.task2.db.Imp.PublisherDaoImp;
import by.book_aston.task2.model.dto.publisher.PublisherDto;
import by.book_aston.task2.service.AuthorService;
import by.book_aston.task2.service.PublisherService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet(name = "PublisherEditName", urlPatterns = "/publisher/edit/name")
public class PublisherEditNameServlet extends HttpServlet {
    private PublisherService publisherService;

    public PublisherEditNameServlet(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    public PublisherEditNameServlet() {
        this.publisherService = new PublisherService(new PublisherDaoImp(ConnectionDB.getConnection()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        publisherService.editName(Integer.valueOf(req.getParameter("id")),req.getParameter("name"));

    }
}

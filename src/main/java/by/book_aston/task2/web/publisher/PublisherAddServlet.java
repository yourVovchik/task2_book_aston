package by.book_aston.task2.web.publisher;

import by.book_aston.task2.config.ConnectionDB;
import by.book_aston.task2.db.Imp.PublisherDaoImp;
import by.book_aston.task2.model.dto.author.AuthorDto;
import by.book_aston.task2.model.dto.publisher.PublisherDto;
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

@WebServlet(name = "PublisherAdd", urlPatterns = "/publisher/add")
public class PublisherAddServlet extends HttpServlet {

    private PublisherService publisherService = new PublisherService(new PublisherDaoImp(ConnectionDB.getConnection()));

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
        PublisherDto publisherDto = new Gson().fromJson(json, PublisherDto.class);
        publisherService.add(publisherDto);

    }
}

package by.book_aston.task2.web.publisher;

import by.book_aston.task2.config.ConnectionDB;
import by.book_aston.task2.db.Imp.BookDaoImp;
import by.book_aston.task2.db.Imp.PublisherDaoImp;
import by.book_aston.task2.service.BookService;
import by.book_aston.task2.service.PublisherService;
import by.book_aston.task2.web.LocalDateAdapter;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

@WebServlet(name = "PublisherGet", urlPatterns = "/publisher/get")
public class PublisherGetServlet  extends HttpServlet {

    private PublisherService publisherService = new PublisherService(new PublisherDaoImp(ConnectionDB.getConnection()));
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        writer.print(new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create().toJson(publisherService.get(Integer.valueOf(req.getParameter("id")))));
        writer.flush();
    }
}

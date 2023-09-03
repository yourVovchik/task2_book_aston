package by.book_aston.task2.web.book;

import by.book_aston.task2.config.ConnectionDB;
import by.book_aston.task2.db.postgresImp.PgBookDao;
import by.book_aston.task2.service.BookService;
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


@WebServlet(name = "BookGet", urlPatterns = "/book/get")
public class BookGetServlet extends HttpServlet {
    private BookService bookService = new BookService(new PgBookDao(ConnectionDB.getConnection()));
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        writer.print(new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create().toJson(bookService.get(Integer.valueOf(req.getParameter("id")))));
        writer.flush();
    }
}

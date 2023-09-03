package by.book_aston.task2.web.book;

import by.book_aston.task2.config.ConnectionDB;
import by.book_aston.task2.db.postgresImp.PgBookDao;
import by.book_aston.task2.model.dto.BookDto;
import by.book_aston.task2.service.BookService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet(name = "BookAdd", urlPatterns = "/book/edit/name")
public class BookEditNameServlet  extends HttpServlet {

    private BookService bookService = new BookService(new PgBookDao(ConnectionDB.getConnection()));
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        bookService.editName(Integer.valueOf(req.getParameter("id")),req.getParameter("name"));
    }
}

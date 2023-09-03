package by.book_aston.task2.web.book;

import by.book_aston.task2.config.ConnectionDB;
import by.book_aston.task2.db.postgres.PgBookDao;
import by.book_aston.task2.model.dto.AuthorDto;
import by.book_aston.task2.model.dto.BookDto;
import by.book_aston.task2.service.BookService;
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

@WebServlet(name = "BookAdd", urlPatterns = "/book/add")
public class BookAddServlet  extends HttpServlet {
    private BookService bookService = new BookService(new PgBookDao(ConnectionDB.getConnection()));
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
        BookDto bookDto = new Gson().fromJson(json, BookDto.class);
        bookService.add(bookDto);
    }
}

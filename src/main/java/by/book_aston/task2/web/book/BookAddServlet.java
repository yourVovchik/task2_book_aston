package by.book_aston.task2.web.book;

import by.book_aston.task2.config.ConnectionDB;
import by.book_aston.task2.db.Imp.BookDaoImp;
import by.book_aston.task2.model.dto.book.BookDto;
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
import java.io.PrintWriter;

@WebServlet(name = "BookAdd", urlPatterns = "/book/add")
public class BookAddServlet  extends HttpServlet {
    private BookService bookService = new BookService(new BookDaoImp(ConnectionDB.getConnection()));
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
        BookDto bookDto = new Gson().fromJson(json, BookDto.class);
        long add = bookService.add(bookDto);

        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        writer.print(add);
        writer.flush();
    }
}

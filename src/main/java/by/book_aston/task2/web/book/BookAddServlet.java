package by.book_aston.task2.web.book;

import by.book_aston.task2.config.ConnectionDB;
import by.book_aston.task2.db.Imp.AuthorDaoImp;
import by.book_aston.task2.db.Imp.BookDaoImp;
import by.book_aston.task2.model.dto.book.BookDto;
import by.book_aston.task2.service.AuthorService;
import by.book_aston.task2.service.BookService;
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

@WebServlet(name = "BookAdd", urlPatterns = "/book/add")
public class BookAddServlet  extends HttpServlet {
    private BookService bookService;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public BookAddServlet(BookService bookService, BufferedReader bufferedReader, PrintWriter printWriter) {
        this.bookService = bookService;
        this.bufferedReader = bufferedReader;
        this.printWriter = printWriter;
    }

    public BookAddServlet() {
        this.bookService = new BookService(new BookDaoImp(ConnectionDB.getConnection()));
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(bufferedReader == null)
            this.bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String json = "";
        json = bufferedReader.readLine();

        BookDto bookDto = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create().fromJson(json, BookDto.class);

        long add = bookService.add(bookDto);

        if(printWriter == null)
            printWriter = resp.getWriter();
        resp.setContentType("application/json");
        printWriter.print(add);
        printWriter.flush();
    }
}

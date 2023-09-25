package by.book_aston.task2.web.servlet.book;

import by.book_aston.task2.config.ConnectionDB;
import by.book_aston.task2.db.Imp.BookDaoImp;
import by.book_aston.task2.model.dto.book.BookDto;
import by.book_aston.task2.service.nativ.BookServiceNativeImp;
import by.book_aston.task2.web.LocalDateAdapter;
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

@WebServlet(name = "BookAdd", urlPatterns = "/book/add")
public class BookAddServlet  extends HttpServlet {
    private BookServiceNativeImp bookServiceNativeImp;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public BookAddServlet(BookServiceNativeImp bookServiceNativeImp, BufferedReader bufferedReader, PrintWriter printWriter) {
        this.bookServiceNativeImp = bookServiceNativeImp;
        this.bufferedReader = bufferedReader;
        this.printWriter = printWriter;
    }

    public BookAddServlet() {
        this.bookServiceNativeImp = new BookServiceNativeImp(new BookDaoImp(ConnectionDB.getConnection()));
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

        long add = bookServiceNativeImp.add(bookDto);

        if(printWriter == null)
            printWriter = resp.getWriter();
        resp.setContentType("application/json");
        printWriter.print(add);
        printWriter.flush();
    }
}

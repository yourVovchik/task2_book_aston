package by.book_aston.task2.web.servlet.book;

import by.book_aston.task2.config.ConnectionDB;
import by.book_aston.task2.db.Imp.BookDaoImp;
import by.book_aston.task2.service.nativ.BookServiceNativeImp;
import by.book_aston.task2.web.LocalDateAdapter;
import com.google.gson.GsonBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;


@WebServlet(name = "BookGet", urlPatterns = "/book/get")
public class BookGetServlet extends HttpServlet {
    private BookServiceNativeImp bookServiceNativeImp = new BookServiceNativeImp(new BookDaoImp(ConnectionDB.getConnection()));
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        writer.print(new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create().toJson(bookServiceNativeImp.get(Integer.valueOf(req.getParameter("id")))));
        writer.flush();
    }
}

package by.book_aston.task2.web.book;

import by.book_aston.task2.config.ConnectionDB;
import by.book_aston.task2.db.Imp.BookDaoImp;
import by.book_aston.task2.service.BookService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "BookAdd", urlPatterns = "/book/edit/name")
public class BookEditNameServlet  extends HttpServlet {

    private BookService bookService ;

    public BookEditNameServlet(BookService bookService) {
        this.bookService = bookService;
    }

    public BookEditNameServlet() {
        this.bookService = new BookService(new BookDaoImp(ConnectionDB.getConnection()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        bookService.editName(Integer.valueOf(req.getParameter("id")),req.getParameter("name"));

        resp.setStatus(200);
    }
}

package by.book_aston.task2.web.servlet.book;

import by.book_aston.task2.config.ConnectionDB;
import by.book_aston.task2.db.Imp.BookDaoImp;
import by.book_aston.task2.service.nativ.BookServiceNativeImp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "BookAdd", urlPatterns = "/book/edit/name")
public class BookEditNameServlet  extends HttpServlet {

    private BookServiceNativeImp bookServiceNativeImp;

    public BookEditNameServlet(BookServiceNativeImp bookServiceNativeImp) {
        this.bookServiceNativeImp = bookServiceNativeImp;
    }

    public BookEditNameServlet() {
        this.bookServiceNativeImp = new BookServiceNativeImp(new BookDaoImp(ConnectionDB.getConnection()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        bookServiceNativeImp.editName(Integer.valueOf(req.getParameter("id")),req.getParameter("name"));

        resp.setStatus(200);
    }
}

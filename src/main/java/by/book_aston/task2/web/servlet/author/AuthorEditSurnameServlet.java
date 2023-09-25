package by.book_aston.task2.web.servlet.author;

import by.book_aston.task2.config.ConnectionDB;
import by.book_aston.task2.db.Imp.AuthorDaoImp;
import by.book_aston.task2.service.nativ.AuthorServiceNativeImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AuthorEditSurname", urlPatterns = "/author/edit/surname")
public class AuthorEditSurnameServlet extends HttpServlet {

    private AuthorServiceNativeImp authorServiceNativeImp;

    public AuthorEditSurnameServlet(AuthorServiceNativeImp authorServiceNativeImp) {
        this.authorServiceNativeImp = authorServiceNativeImp;
    }

    public AuthorEditSurnameServlet() {
        this.authorServiceNativeImp = new AuthorServiceNativeImp(new AuthorDaoImp(ConnectionDB.getConnection()));
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorServiceNativeImp.editSurname(Integer.valueOf(req.getParameter("id")),req.getParameter("surname"));
        resp.setStatus(200);
    }
}

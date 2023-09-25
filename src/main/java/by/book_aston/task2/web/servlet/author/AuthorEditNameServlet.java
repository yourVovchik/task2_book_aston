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

@WebServlet(name = "AuthorEditName", urlPatterns = "/author/edit/name")
public class AuthorEditNameServlet extends HttpServlet {

    private AuthorServiceNativeImp authorServiceNativeImp;

    public AuthorEditNameServlet(AuthorServiceNativeImp authorServiceNativeImp) {
        this.authorServiceNativeImp = authorServiceNativeImp;
    }

    public AuthorEditNameServlet() {
        this.authorServiceNativeImp = new AuthorServiceNativeImp(new AuthorDaoImp(ConnectionDB.getConnection()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorServiceNativeImp.editName(Integer.valueOf(req.getParameter("id")),req.getParameter("name"));

        resp.setStatus(200);
    }
}

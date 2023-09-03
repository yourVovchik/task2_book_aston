package by.book_aston.task2.web.author;

import by.book_aston.task2.config.ConnectionDB;
import by.book_aston.task2.db.postgresImp.PgAuthorDao;
import by.book_aston.task2.service.AuthorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AuthorEditSurname", urlPatterns = "/author/edit/surname")
public class AuthorEditSurnameServlet extends HttpServlet {

    private AuthorService authorService = new AuthorService(new PgAuthorDao(ConnectionDB.getConnection()));
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorService.editSurname(Integer.valueOf(req.getParameter("id")),req.getParameter("surname"));
    }
}

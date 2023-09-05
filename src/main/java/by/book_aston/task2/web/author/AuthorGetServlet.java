package by.book_aston.task2.web.author;

import by.book_aston.task2.config.ConnectionDB;
import by.book_aston.task2.db.Imp.AuthorDaoImp;
import by.book_aston.task2.service.AuthorService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AuthorGet", urlPatterns = "/author/get")
public class AuthorGetServlet extends HttpServlet {

    private AuthorService authorService;

    public AuthorGetServlet(AuthorService authorService) {
        this.authorService = authorService;
    }

    public AuthorGetServlet() {
        this.authorService = new AuthorService(new AuthorDaoImp(ConnectionDB.getConnection()));
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        writer.print(new Gson().toJson(authorService.get(Integer.valueOf(req.getParameter("id")))));
        writer.flush();
    }
}

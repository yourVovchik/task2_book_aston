package by.book_aston.task2.web.servlet.author;

import by.book_aston.task2.config.ConnectionDB;
import by.book_aston.task2.db.Imp.AuthorDaoImp;
import by.book_aston.task2.service.nativ.AuthorServiceNativeImp;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AuthorGet", urlPatterns = "/author/get")
public class AuthorGetServlet extends HttpServlet {

    private AuthorServiceNativeImp authorServiceNativeImp;

    public AuthorGetServlet(AuthorServiceNativeImp authorServiceNativeImp) {
        this.authorServiceNativeImp = authorServiceNativeImp;
    }

    public AuthorGetServlet() {
        this.authorServiceNativeImp = new AuthorServiceNativeImp(new AuthorDaoImp(ConnectionDB.getConnection()));
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        writer.print(new Gson().toJson(authorServiceNativeImp.get(Integer.valueOf(req.getParameter("id")))));
        writer.flush();
    }
}

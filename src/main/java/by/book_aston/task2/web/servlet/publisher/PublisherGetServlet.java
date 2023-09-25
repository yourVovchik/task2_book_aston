package by.book_aston.task2.web.servlet.publisher;

import by.book_aston.task2.config.ConnectionDB;
import by.book_aston.task2.db.Imp.PublisherDaoImp;
import by.book_aston.task2.service.nativ.PublisherServiceNativeImp;
import com.google.gson.GsonBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PublisherGet", urlPatterns = "/publisher/get")
public class PublisherGetServlet  extends HttpServlet {

    private PublisherServiceNativeImp publisherServiceNativeImp = new PublisherServiceNativeImp(new PublisherDaoImp(ConnectionDB.getConnection()));
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        writer.print(new GsonBuilder()
                .setPrettyPrinting()
                .create().toJson(publisherServiceNativeImp.get(Integer.valueOf(req.getParameter("id")))));
        writer.flush();
    }
}

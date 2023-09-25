package by.book_aston.task2.web.servlet.publisher;

import by.book_aston.task2.config.ConnectionDB;
import by.book_aston.task2.db.Imp.PublisherDaoImp;
import by.book_aston.task2.service.nativ.PublisherServiceNativeImp;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "PublisherEditName", urlPatterns = "/publisher/edit/name")
public class PublisherEditNameServlet extends HttpServlet {
    private PublisherServiceNativeImp publisherServiceNativeImp;

    public PublisherEditNameServlet(PublisherServiceNativeImp publisherServiceNativeImp) {
        this.publisherServiceNativeImp = publisherServiceNativeImp;
    }

    public PublisherEditNameServlet() {
        this.publisherServiceNativeImp = new PublisherServiceNativeImp(new PublisherDaoImp(ConnectionDB.getConnection()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        publisherServiceNativeImp.editName(Integer.valueOf(req.getParameter("id")),req.getParameter("name"));

    }
}

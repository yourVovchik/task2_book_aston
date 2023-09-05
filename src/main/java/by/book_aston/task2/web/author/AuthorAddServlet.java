package by.book_aston.task2.web.author;

import by.book_aston.task2.config.ConnectionDB;
import by.book_aston.task2.db.Imp.AuthorDaoImp;
import by.book_aston.task2.model.dto.author.AuthorDto;
import by.book_aston.task2.service.AuthorService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@WebServlet(name = "AuthorAdd", urlPatterns = "/author/add")
public class AuthorAddServlet extends HttpServlet {
    private AuthorService authorService = new AuthorService(new AuthorDaoImp(ConnectionDB.getConnection()));

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }

        AuthorDto authorDto = new Gson().fromJson(json, AuthorDto.class);
        long add = authorService.add(authorDto);

        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        writer.print(add);
        writer.flush();
    }
}

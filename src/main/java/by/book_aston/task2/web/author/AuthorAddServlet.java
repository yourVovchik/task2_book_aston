package by.book_aston.task2.web.author;

import by.book_aston.task2.config.ConnectionDB;
import by.book_aston.task2.db.postgresImp.PgAuthorDao;
import by.book_aston.task2.model.dto.AuthorDto;
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

@WebServlet(name = "AuthorAdd", urlPatterns = "/author/add")
public class AuthorAddServlet extends HttpServlet {
    private AuthorService authorService = new AuthorService(new PgAuthorDao(ConnectionDB.getConnection()));

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
        AuthorDto authorDto = new Gson().fromJson(json, AuthorDto.class);
        authorService.add(authorDto);

    }
}

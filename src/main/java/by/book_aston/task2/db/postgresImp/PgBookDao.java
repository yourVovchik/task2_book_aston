package by.book_aston.task2.db.postgresImp;

import by.book_aston.task2.db.BookDao;
import by.book_aston.task2.mapper.BookMapper;
import by.book_aston.task2.model.entity.Author;
import by.book_aston.task2.model.entity.Book;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class PgBookDao implements BookDao {
    private final Connection connection;

    public PgBookDao(Connection connection) {
        this.connection = connection;
    }
    @Override
    public Book get(long id) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT b.id, b.name, b.publication_date, a.id AS author_id, a.name AS author_name, a.surname AS author_surname " +
                            "FROM book b LEFT JOIN author_book atb ON b.id = atb.book_id LEFT JOIN author a ON atb.author_id = a.id WHERE b.id = ?;");
            ps.setLong(1,id);
            ResultSet resultSet = ps.executeQuery();
            return BookMapper.parseBookListWithAuthors(resultSet).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public long add(Book book) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO book (id,name,publication_date) VALUES (DEFAULT,?,?) RETURNING id;");
            ps.setString(1,book.getName());
            ps.setDate(2,java.sql.Date.valueOf(book.getPublicationDate()));
            ResultSet resultSet = ps.executeQuery();
            for(Author author : book.getAuthors()){
                PreparedStatement psAtb = connection.prepareStatement("INSERT INTO author_book (author_id, book_id) VALUES (?,?);");
                psAtb.setLong(1,author.getId());
                psAtb.setLong(2,book.getId());
                psAtb.execute();
            }
            resultSet.next();
            return resultSet.getLong("id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(long id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM book WHERE id = ?;");
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM author_book WHERE book_id = ?;");
            ps.setLong(1,id);
            ps1.setLong(1,id);
            ps1.execute();
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void editName(long id, String name) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE book SET name = ? WHERE id = ?;");
            ps.setString(1,name);
            ps.setLong(2,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editPublicationDate(long id, LocalDate localDate) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE book SET publication_date = ? WHERE id = ?;");
            ps.setDate(1, Date.valueOf(localDate));
            ps.setLong(2,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editAuthors(long id, List<Author> authors) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM author_book WHERE book_id = ?;");
            ps.setLong(1,id);
            ps.execute();
            for(Author author : authors){
                PreparedStatement psAtb = connection.prepareStatement("INSERT INTO author_book (author_id, book_id) VALUES (?,?);");
                psAtb.setLong(1,author.getId());
                psAtb.setLong(2,id);
                psAtb.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean containsId(long id) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT id FROM book WHERE id = ?;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setLong(1,id);
            return ps.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}

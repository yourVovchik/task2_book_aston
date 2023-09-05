package by.book_aston.task2.db.Imp;

import by.book_aston.task2.db.AuthorDao;
import by.book_aston.task2.mapper.AuthorMapper;
import by.book_aston.task2.model.entity.Author;
import by.book_aston.task2.model.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AuthorDaoImp implements AuthorDao {

    private final Connection connection;

    public AuthorDaoImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Author get(long id) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT a.id, a.name, a.surname, b.id AS book_id, b.name AS book_name, b.publication_date AS book_publication_date FROM author a LEFT JOIN author_book atb ON a.id = atb.author_id LEFT JOIN book b ON atb.book_id = b.id WHERE a.id = ?;");
            ps.setLong(1,id);
            ResultSet resultSet = ps.executeQuery();
            return AuthorMapper.toAuthorListFromResultSet(resultSet).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long add(Author author) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO author (name,surname,publisher_id) VALUES (?,?,?) RETURNING id;");
            ps.setString(1,author.getName());
            ps.setString(2,author.getSurname());
            ps.setLong(3,author.getPublisher().getId());
            ResultSet resultSet = ps.executeQuery();
            for(Book book : author.getBookList()){
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
            PreparedStatement ps = connection.prepareStatement("DELETE FROM author WHERE id = ?;");
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM author_book WHERE author_id = ?;");
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
            PreparedStatement ps = connection.prepareStatement("UPDATE author SET name = ? WHERE id = ?;");
            ps.setString(1,name);
            ps.setLong(2,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editBooks(long id, List<Book> books) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM author_book WHERE author_id = ?;");
            ps.setLong(1,id);
            ps.execute();
            for(Book book : books){
                PreparedStatement psAtb = connection.prepareStatement("INSERT INTO author_book (author_id, book_id) VALUES (?,?);");
                psAtb.setLong(1,id);
                psAtb.setLong(2,book.getId());
                psAtb.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editSurname(long id, String surname) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE author SET surname = ? WHERE id = ?;");
            ps.setString(1,surname);
            ps.setLong(2,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean containsId(long id) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT id FROM author WHERE id = ?;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setLong(1,id);
            return ps.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

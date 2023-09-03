package by.book_aston.task2.db.postgres;

import by.book_aston.task2.db.AuthorDao;
import by.book_aston.task2.mapper.AuthorMapper;
import by.book_aston.task2.model.entity.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PgAuthorDao implements AuthorDao {

    private final Connection connection;

    public PgAuthorDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Author get(long id) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT id, name, surname FROM author WHERE id = ?;");
            ps.setLong(1,id);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            return AuthorMapper.toAuthorFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long add(Author author) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO author (name,surname) VALUES (?,?) RETURNING id;");
            ps.setString(1,author.getName());
            ps.setString(2,author.getSurname());
            ResultSet resultSet = ps.executeQuery();
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
            ps.setLong(1,id);
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

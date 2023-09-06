package by.book_aston.task2.db.Imp;

import by.book_aston.task2.config.ConnectionDB;
import by.book_aston.task2.db.PublisherDao;
import by.book_aston.task2.mapper.PublisherMapper;
import by.book_aston.task2.model.entity.Author;
import by.book_aston.task2.model.entity.Publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PublisherDaoImp implements PublisherDao {


    @Override
    public Publisher get(long id) {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT p.id, p.name, a.id AS author_id, a.name AS author_name, a.surname AS author_surname FROM publisher p LEFT JOIN author a ON p.id = a.publisher_id WHERE p.id = ?");
            ps.setLong(1,id);
            ResultSet resultSet = ps.executeQuery();
            return PublisherMapper.toPublisherListFromResultSet(resultSet).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long add(Publisher publisher) {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO publisher (name) VALUES (?) RETURNING id;");
            ps.setString(1, publisher.getName());
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            return resultSet.getLong("id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM publisher WHERE id = ?;");
            ps.setLong(1,id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void editName(long id, String name) {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE publisher SET name = ? WHERE id = ?;");
            ps.setString(1,name);
            ps.setLong(2,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editAuthors(long id, List<Author> authorList) {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM author WHERE publisher_id = ?;");
            ps.setLong(1,id);
            ps.execute();
            for(Author author : authorList){
                PreparedStatement psAtb = connection.prepareStatement("INSERT INTO author (id, name, surname, publisher_id) VALUES (?,?,?,?);");
                psAtb.setLong(1,author.getId());
                psAtb.setString(2,author.getName());
                psAtb.setString(3,author.getSurname());
                psAtb.setLong(4,id);
                psAtb.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean containsId(long id) {
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT id FROM publisher WHERE id = ?;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setLong(1,id);
            return ps.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

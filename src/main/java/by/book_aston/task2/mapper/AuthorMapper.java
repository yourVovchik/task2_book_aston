package by.book_aston.task2.mapper;

import by.book_aston.task2.model.dto.AuthorDto;
import by.book_aston.task2.model.entity.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper {
    public static Author toAuthorFromResultSet(ResultSet resultSet) throws SQLException {
        return new Author(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("surname"));
    }

    public static Author toAuthorFromDto(AuthorDto authorDto){
        return new Author(
                authorDto.getId(),
                authorDto.getName(),
                authorDto.getSurname()
        );
    }

    public static AuthorDto fromAuthorToDto(Author author){
        return new AuthorDto(
                author.getId(),
                author.getName(),
                author.getSurname()
        );
    }

}

package by.book_aston.task2.mapper;

import by.book_aston.task2.model.dto.publisher.AuthorPublishersDto;
import by.book_aston.task2.model.dto.publisher.PublisherDto;
import by.book_aston.task2.model.entity.Author;
import by.book_aston.task2.model.entity.Publisher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PublisherMapper {

    public static List<Publisher> toPublisherListFromResultSet(ResultSet resultSet) throws SQLException {
        Map<Long,Publisher> publisherMap = new HashMap<>();
        while (resultSet.next()){
            long id = resultSet.getLong("id");
            Publisher publisher;
            if(!publisherMap.containsKey(id)){
                publisher = toPublisherFromResultSet(resultSet);
                publisher.setAuthorList(new ArrayList<>());
            }else{
                publisher = publisherMap.get(id);
            }
            publisher.getAuthorList().add(toAuthorFromResultSet(resultSet));
            publisherMap.put(id,publisher);
        }
        return new ArrayList<>(publisherMap.values());
    }

    public static Publisher toPublisherFromResultSet(ResultSet resultSet) throws SQLException {
        return new Publisher(resultSet.getLong("id"),resultSet.getString("name"),null);
    }

    public static PublisherDto toPublisherDtoFromPublisher(Publisher publisher){
        return new PublisherDto(publisher.getId(),publisher.getName(), toAuthorPublishersDtoListFromAuthorList(publisher.getAuthorList()));
    }

    public static Publisher toPublisherFromPublisherDto(PublisherDto publisherDto){
        return new Publisher(
                publisherDto.getId(),
                publisherDto.getName(),
                toAuthorListFromAuthorPublishersDtoList(publisherDto.getPublisherAuthorDtoList()));
    }

    public static List<AuthorPublishersDto> toAuthorPublishersDtoListFromAuthorList(List<Author> authorList){
        List<AuthorPublishersDto> authorPublishersDtoList = new ArrayList<>();
        for(Author author : authorList){
            authorPublishersDtoList.add(toAuthorPublishersDtoFromAuthor(author));
        }
        return authorPublishersDtoList;
    }

    public static AuthorPublishersDto toAuthorPublishersDtoFromAuthor(Author author){
        return new AuthorPublishersDto(author.getId(), author.getName(), author.getSurname());
    }
    public static List<Author> toAuthorListFromAuthorPublishersDtoList(List<AuthorPublishersDto> authorPublishersDtoList){
        List<Author> authorList = new ArrayList<>();
        for(AuthorPublishersDto authorPublishersDto : authorPublishersDtoList){
            authorList.add(toAuthorFromAuthorPublishersDto(authorPublishersDto));
        }
        return authorList;
    }

    public static Author toAuthorFromAuthorPublishersDto(AuthorPublishersDto authorPublishersDto){
        return new Author(
                authorPublishersDto.getId(),
                authorPublishersDto.getName(),
                authorPublishersDto.getSurname());
    }

    public static Author toAuthorFromResultSet(ResultSet resultSet) throws SQLException {
        return new Author(
                resultSet.getLong("author_id"),
                resultSet.getString("author_name"),
                resultSet.getString("author_surname"));
    }
}

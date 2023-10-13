package by.book_aston.task2.model.dto.publisher;

import java.util.List;

public class PublisherDto {
    private long id;
    private String name;
    private List<AuthorPublishersDto> authorPublishersDtoList;

    public PublisherDto(long id, String name, List<AuthorPublishersDto> authorPublishersDtoList) {
        this.id = id;
        this.name = name;
        this.authorPublishersDtoList = authorPublishersDtoList;
    }

    public PublisherDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AuthorPublishersDto> getPublisherAuthorDtoList() {
        return authorPublishersDtoList;
    }

    public void setPublisherAuthorDtoList(List<AuthorPublishersDto> authorPublishersDtoList) {
        this.authorPublishersDtoList = authorPublishersDtoList;
    }
}

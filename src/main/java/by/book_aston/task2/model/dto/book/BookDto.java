package by.book_aston.task2.model.dto.book;

import java.time.LocalDate;
import java.util.List;

public class BookDto {
    private long id;
    private String name;

    private LocalDate publicationDate;
    private List<AuthorBooksDto> authorBooksDtoList;

    public BookDto(long id, String name, LocalDate publicationDate, List<AuthorBooksDto> authorBooksDtoList) {
        this.id = id;
        this.name = name;
        this.publicationDate = publicationDate;
        this.authorBooksDtoList = authorBooksDtoList;
    }

    public BookDto() {
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

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<AuthorBooksDto> getAuthors() {
        return authorBooksDtoList;
    }

    public void setAuthors(List<AuthorBooksDto> authors) {
        this.authorBooksDtoList = authors;
    }
}

package by.book_aston.task2.model.dto;

import by.book_aston.task2.model.entity.Author;

import java.time.LocalDate;
import java.util.List;

public class BookDto {
    private long id;
    private String name;

    private LocalDate publicationDate;
    private List<Author> authors;

    public BookDto(long id, String name, LocalDate publicationDate, List<Author> authors) {
        this.id = id;
        this.name = name;
        this.publicationDate = publicationDate;
        this.authors = authors;
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

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}

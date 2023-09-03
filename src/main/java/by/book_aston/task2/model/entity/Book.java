package by.book_aston.task2.model.entity;

import java.time.LocalDate;
import java.util.List;

public class Book {
    private long id;
    private String name;

    private LocalDate publicationDate;
    private List<Author> authors;

    public Book(long id, String name, LocalDate publicationDate, List<Author> authors) {
        this.id = id;
        this.name = name;
        this.publicationDate = publicationDate;
        this.authors = authors;
    }

    public Book(long id, String name, LocalDate publicationDate) {
        this.id = id;
        this.name = name;
        this.publicationDate = publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
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

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}

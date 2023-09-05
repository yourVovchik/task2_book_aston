package by.book_aston.task2.model.dto.author;

import java.time.LocalDate;

public class BookAuthorsDto {
    private long id;
    private String name;
    private LocalDate publicationDate;

    public BookAuthorsDto(long id, String name, LocalDate publicationDate) {
        this.id = id;
        this.name = name;
        this.publicationDate = publicationDate;
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
}

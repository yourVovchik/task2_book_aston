package by.book_aston.task2.model.dto.author;

import java.util.List;

public class AuthorDto {
    private long id;
    private String name;
    private String surname;

    private List<BookAuthorsDto> bookAuthorsDtoList;

    public AuthorDto(long id, String name, String surname, List<BookAuthorsDto> bookAuthorsDtoList) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.bookAuthorsDtoList = bookAuthorsDtoList;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}

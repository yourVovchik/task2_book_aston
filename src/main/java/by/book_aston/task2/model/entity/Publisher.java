package by.book_aston.task2.model.entity;

import java.util.List;

public class Publisher {
    private long id;
    private String name;
    private List<Author> authorList;

    public Publisher(long id, String name, List<Author> authorList) {
        this.id = id;
        this.name = name;
        this.authorList = authorList;
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

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }
}

package by.book_aston.task2.model.entity;

import java.util.List;

public class Author {
    private long id;
    private String name;
    private String surname;
    private Publisher publisher;
    private List<Book> bookList;

    public Author(long id, String name, String surname,Publisher publisher, List<Book> bookList) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.publisher = publisher;
        this.bookList = bookList;
    }

    public Author(long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
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

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}

package by.book_aston.task2.model.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;

    @ManyToOne
    @JoinColumn(name="publisher_id", nullable=false)
    private Publisher publisher;

    @ManyToMany
    @JoinTable(
            name = "author_book",
            joinColumns = { @JoinColumn(name = "author_id") },
            inverseJoinColumns = { @JoinColumn(name = "book_id") }
    )
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

    public Author() {
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

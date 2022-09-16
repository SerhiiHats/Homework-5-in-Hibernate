package models;

import javax.persistence.*;

@Entity
@Table(name = "books_authors")
public class BookAuthor {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name_book")
    private String nameBook;
    @Column(name = "name")
    private String nameAuthor;
    @Column(name = "last_name")
    private String lastNameAuthor;

    public BookAuthor() {
    }

    public BookAuthor(String nameBook, String nameAuthor, String lastNameAuthor) {
        this.nameBook = nameBook;
        this.nameAuthor = nameAuthor;
        this.lastNameAuthor = lastNameAuthor;
    }

    public BookAuthor(long id, String nameBook, String nameAuthor, String lastNameAuthor) {
        this.id = id;
        this.nameBook = nameBook;
        this.nameAuthor = nameAuthor;
        this.lastNameAuthor = lastNameAuthor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    public String getLastNameAuthor() {
        return lastNameAuthor;
    }

    public void setLastNameAuthor(String lastNameAuthor) {
        this.lastNameAuthor = lastNameAuthor;
    }

    @Override
    public String toString() {
        return "BookAuthor{" +
                "id=" + id +
                ", nameBook='" + nameBook + '\'' +
                ", nameAuthor='" + nameAuthor + '\'' +
                ", lastNameAuthor='" + lastNameAuthor + '\'' +
                '}';
    }
}

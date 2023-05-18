package dev.bookstore.library.credit;

import dev.bookstore.library.author.Author;
import dev.bookstore.library.book.Book;
import jakarta.persistence.*;

@Entity
@Table(name = "credit")
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credit_id")
    private Long id;

    // Relation with book
    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    // Relation with author
    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;

    public Credit() {
    }

    public Credit(Book book, Author author) {
        this.book = book;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}

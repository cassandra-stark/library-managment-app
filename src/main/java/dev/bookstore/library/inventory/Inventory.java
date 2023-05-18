package dev.bookstore.library.inventory;

import dev.bookstore.library.book.Book;
import dev.bookstore.library.library.Library;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Long id;

    // Association with books
    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Book> books = new ArrayList<>();

    // Association with library
    @OneToOne
    private Library library;

    public Inventory() {
    }

    public Inventory(List<Book> books, Library library) {
        this.books = books;
        this.library = library;
    }

    public Long getId() {
        return id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
}

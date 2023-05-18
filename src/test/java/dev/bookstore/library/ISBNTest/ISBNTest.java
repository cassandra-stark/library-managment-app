package dev.bookstore.library.ISBNTest;

import dev.bookstore.library.book.Book;
import dev.bookstore.library.isbn.ISBN;
import dev.bookstore.library.isbn.ISBNRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class ISBNTest {

    @Autowired
    private ISBNRepository isbnRepository;

    private ISBN isbn;
    private Book book;

    @BeforeEach
    public void setUp() {
        isbn = new ISBN();
        isbn.setNumber("123456789L");

        book = new Book();
        book.setTitle("Book Title");

        isbn.setBook(book);
        book.setIsbn(isbn);

        isbnRepository.save(isbn);

    }

    @Test
    public void isbnCanBeAdded() {
        ISBN savedISBN = isbnRepository.findById(isbn.getId()).get();

        assertNotNull(savedISBN.getId());
        assertThat(savedISBN.getNumber(), equalTo(isbn.getNumber()));
    }

    @Test
    public void isbnCanBeUpdated() {
        ISBN savedISBN = isbnRepository.findById(isbn.getId()).get();

        savedISBN.setNumber("987654321L");
        isbnRepository.save(savedISBN);

        ISBN updatedISBN = isbnRepository.findById(savedISBN.getId()).get();

        assertThat(updatedISBN.getNumber(), equalTo(savedISBN.getNumber()));
    }

    @Test
    public void isbnCanBeDeleted() {
        ISBN savedISBN = isbnRepository.findById(isbn.getId()).get();

        book.removeIsbn();
        isbnRepository.delete(savedISBN);

        assertEquals(0, isbnRepository.count());
    }
}

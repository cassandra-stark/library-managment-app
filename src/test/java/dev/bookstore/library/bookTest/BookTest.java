package dev.bookstore.library.bookTest;

import dev.bookstore.library.author.Author;
import dev.bookstore.library.author.AuthorRepository;
import dev.bookstore.library.book.Book;
import dev.bookstore.library.book.BookRepository;
import dev.bookstore.library.credit.Credit;
import dev.bookstore.library.credit.CreditRepository;
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
public class BookTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private ISBNRepository isbnRepository;

    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setTitle("Test title");

        ISBN isbn = new ISBN();
        isbn.setNumber("123456789L");
        isbn.setBook(book);
        book.setIsbn(isbn);

        Credit credit = new Credit();
        credit.setBook(book);

        Author author = new Author();
        author.setFirstName("Test author");
        author.setLastName("Test surname");

        credit.setAuthor(author);
        author.addCredit(credit);

        isbnRepository.save(isbn);
        authorRepository.save(author);
        creditRepository.save(credit);

        book.addCredit(credit);

        bookRepository.save(book);
    }

    @Test
    public void saveBook() {
        Book savedBook = bookRepository.findById(book.getId()).get();

        assertNotNull(book);
        assertThat(savedBook.getId(), equalTo(book.getId()));
        assertThat(savedBook.getTitle(), equalTo(book.getTitle()));
        assertThat(savedBook.getIsbn().getNumber(), equalTo(book.getIsbn().getNumber()));
        assertThat(savedBook.getCredits().get(0).getAuthor().getFirstName(), equalTo(book.getCredits().get(0).getAuthor().getFirstName()));
        assertThat(savedBook.getCredits().get(0).getAuthor().getLastName(), equalTo(book.getCredits().get(0).getAuthor().getLastName()));
    }

    @Test
    public void updateBook() {
        Book savedBook = bookRepository.findById(book.getId()).get();
        savedBook.setTitle("Updated title");
        bookRepository.save(savedBook);

        Book updatedBook = bookRepository.findById(book.getId()).get();
        assertThat(updatedBook.getTitle(), equalTo(savedBook.getTitle()));
    }

    @Test
    public void deleteBook() {
        Book savedBook = bookRepository.findById(book.getId()).get();
        isbnRepository.delete(savedBook.getIsbn());
        authorRepository.delete(savedBook.getCredits().get(0).getAuthor());
        creditRepository.delete(savedBook.getCredits().get(0));
        bookRepository.delete(savedBook);

        assertEquals(0, bookRepository.count());
    }

}
